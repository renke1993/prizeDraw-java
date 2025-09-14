package com.example.ethereum.contract.generated;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/LFDT-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.7.0.
 */
@SuppressWarnings("rawtypes")
public class PrizeDraw extends Contract {
    public static final String BINARY = "6080604052348015600e575f5ffd5b5061022d8061001c5f395ff3fe608060405234801561000f575f5ffd5b5060043610610029575f3560e01c8063be9a65551461002d575b5f5ffd5b610035610037565b005b5f600a424360405160200161004d92919061011f565b604051602081830303815290604052805190602001205f1c61006f9190610177565b905060058111156100b9577ffa0974f074f651f1ec33d39cf7d160a25b998d5aee785ba1fc843b33125c4dde8160016040516100ac9291906101d0565b60405180910390a16100f3565b7ffa0974f074f651f1ec33d39cf7d160a25b998d5aee785ba1fc843b33125c4dde815f6040516100ea9291906101d0565b60405180910390a15b50565b5f819050919050565b5f819050919050565b610119610114826100f6565b6100ff565b82525050565b5f61012a8285610108565b60208201915061013a8284610108565b6020820191508190509392505050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52601260045260245ffd5b5f610181826100f6565b915061018c836100f6565b92508261019c5761019b61014a565b5b828206905092915050565b6101b0816100f6565b82525050565b5f8115159050919050565b6101ca816101b6565b82525050565b5f6040820190506101e35f8301856101a7565b6101f060208301846101c1565b939250505056fea2646970667358221220ff154b7709587043d1d8a1ac8d37306ebf28d272e13a388af7a57f330355874064736f6c634300081e0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_START = "start";

    public static final Event PRIZERESULT_EVENT = new Event("PrizeResult", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
    ;

    @Deprecated
    protected PrizeDraw(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PrizeDraw(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PrizeDraw(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PrizeDraw(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<PrizeResultEventResponse> getPrizeResultEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PRIZERESULT_EVENT, transactionReceipt);
        ArrayList<PrizeResultEventResponse> responses = new ArrayList<PrizeResultEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PrizeResultEventResponse typedResponse = new PrizeResultEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.prizeNumber = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.isWin = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PrizeResultEventResponse getPrizeResultEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PRIZERESULT_EVENT, log);
        PrizeResultEventResponse typedResponse = new PrizeResultEventResponse();
        typedResponse.log = log;
        typedResponse.prizeNumber = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.isWin = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<PrizeResultEventResponse> prizeResultEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPrizeResultEventFromLog(log));
    }

    public Flowable<PrizeResultEventResponse> prizeResultEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PRIZERESULT_EVENT));
        return prizeResultEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> start() {
        final Function function = new Function(
                FUNC_START, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static PrizeDraw load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new PrizeDraw(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PrizeDraw load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PrizeDraw(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PrizeDraw load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new PrizeDraw(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PrizeDraw load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PrizeDraw(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PrizeDraw> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PrizeDraw.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<PrizeDraw> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PrizeDraw.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<PrizeDraw> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PrizeDraw.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<PrizeDraw> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PrizeDraw.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class PrizeResultEventResponse extends BaseEventResponse {
        public BigInteger prizeNumber;

        public Boolean isWin;
    }
}
