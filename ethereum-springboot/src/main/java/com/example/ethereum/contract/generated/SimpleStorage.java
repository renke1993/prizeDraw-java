package com.example.ethereum.contract.generated;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
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
public class SimpleStorage extends Contract {
    public static final String BINARY = "6080604052348015600e575f5ffd5b506101d38061001c5f395ff3fe608060405234801561000f575f5ffd5b5060043610610034575f3560e01c806320965255146100385780635524107714610056575b5f5ffd5b610040610072565b60405161004d91906100d3565b60405180910390f35b610070600480360381019061006b919061011a565b61007a565b005b5f5f54905090565b805f81905550807fc53a6612a7428e1fc89cb87169d69d64eaefe94f5b45d43f80f1ad9d7065d2c8336040516100b09190610184565b60405180910390a250565b5f819050919050565b6100cd816100bb565b82525050565b5f6020820190506100e65f8301846100c4565b92915050565b5f5ffd5b6100f9816100bb565b8114610103575f5ffd5b50565b5f81359050610114816100f0565b92915050565b5f6020828403121561012f5761012e6100ec565b5b5f61013c84828501610106565b91505092915050565b5f73ffffffffffffffffffffffffffffffffffffffff82169050919050565b5f61016e82610145565b9050919050565b61017e81610164565b82525050565b5f6020820190506101975f830184610175565b9291505056fea2646970667358221220077d8dde4e2badf7a815723c5ee40c914589593208a0570661beabd4183fe31464736f6c634300081e0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_GETVALUE = "getValue";

    public static final String FUNC_SETVALUE = "setValue";

    public static final Event VALUECHANGED_EVENT = new Event("ValueChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected SimpleStorage(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SimpleStorage(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SimpleStorage(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SimpleStorage(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ValueChangedEventResponse> getValueChangedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(VALUECHANGED_EVENT, transactionReceipt);
        ArrayList<ValueChangedEventResponse> responses = new ArrayList<ValueChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ValueChangedEventResponse typedResponse = new ValueChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newValue = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.changedBy = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ValueChangedEventResponse getValueChangedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(VALUECHANGED_EVENT, log);
        ValueChangedEventResponse typedResponse = new ValueChangedEventResponse();
        typedResponse.log = log;
        typedResponse.newValue = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.changedBy = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<ValueChangedEventResponse> valueChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getValueChangedEventFromLog(log));
    }

    public Flowable<ValueChangedEventResponse> valueChangedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALUECHANGED_EVENT));
        return valueChangedEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> getValue() {
        final Function function = new Function(FUNC_GETVALUE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setValue(BigInteger _value) {
        final Function function = new Function(
                FUNC_SETVALUE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static SimpleStorage load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleStorage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SimpleStorage load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleStorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SimpleStorage load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new SimpleStorage(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SimpleStorage load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SimpleStorage(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SimpleStorage> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SimpleStorage.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SimpleStorage> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SimpleStorage.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<SimpleStorage> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SimpleStorage.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SimpleStorage> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SimpleStorage.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class ValueChangedEventResponse extends BaseEventResponse {
        public BigInteger newValue;

        public String changedBy;
    }
}
