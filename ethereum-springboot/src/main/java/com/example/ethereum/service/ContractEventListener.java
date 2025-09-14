package com.example.ethereum.service;

import com.example.ethereum.config.EthereumConfig;
import com.example.ethereum.contract.generated.SimpleStorage;
import io.reactivex.disposables.Disposable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeEncoder;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.utils.Numeric;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.math.BigInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContractEventListener {

    private final Web3j web3j;
    private final Credentials credentials;
    private final ContractGasProvider gasProvider;
    private final EthereumConfig ethereumConfig;
    
    private SimpleStorage contract;
    private Disposable eventSubscription;
    
    @PostConstruct
    public void init() {
        contract = SimpleStorage.load(
                ethereumConfig.getContractAddress(),
                web3j,
                credentials,
                gasProvider
        );
        EthFilter filter = new EthFilter(
                DefaultBlockParameter.valueOf("latest"),
                DefaultBlockParameter.valueOf("latest"),
                contract.getContractAddress()
        );

        // 只添加事件签名作为主题
        filter.addSingleTopic(EventEncoder.encode(SimpleStorage.VALUECHANGED_EVENT));
        // 添加索引value值过滤
        BigInteger value1 = BigInteger.valueOf(100);
        String valueHex = "0x" + Numeric.toHexStringNoPrefixZeroPadded(value1, 64);
        filter.addSingleTopic(valueHex);

        // 订阅ValueChanged事件
        eventSubscription = contract.valueChangedEventFlowable(
                        filter)
                .subscribe(
                        event -> {
                            log.info("ValueChanged事件触发:");
                            log.info("新值: {}", event.newValue);
                            log.info("修改者: {}", event.changedBy);
                            
                            // 在这里可以添加自定义的业务逻辑处理
                        },
                        error -> log.error("事件监听发生错误", error)
                );
        
        log.info("合约事件监听已启动");
    }
    
    @PreDestroy
    public void cleanup() {
        if (eventSubscription != null && !eventSubscription.isDisposed()) {
            eventSubscription.dispose();
            log.info("合约事件监听已关闭");
        }
    }
}
