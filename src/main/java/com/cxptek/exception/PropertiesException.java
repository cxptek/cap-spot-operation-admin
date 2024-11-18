package com.cxptek.exception;

import static java.lang.String.format;

public class PropertiesException extends Exception {
    private static final long serialVersionUID = 3087589154485921267L;

    public PropertiesException(Throwable e) {
        super(e);
    }

    public PropertiesException(String msg) {
        super(msg);
    }

    public static PropertiesException cantFindChain(String chainCode) {
        return new PropertiesException(format("Chain %s cant find or has been deactivated", chainCode));
    }

    public static PropertiesException cantFindSystemAccount(String accountName) {
        return new PropertiesException(format("Cant find System Account by accountName: %s", accountName));
    }

    public static PropertiesException cantFindSmartContract(String contractName) {
        return new PropertiesException(format("Cant find SmartContract Address by contractName: %s", contractName));
    }
}
