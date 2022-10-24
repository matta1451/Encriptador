package com.encryptor.encriptacion.services;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("EncriptadorServiceImpl")
public class EncriptadorServiceImpl implements EncriptadorService{

    private StandardPBEStringEncryptor standardPBEStringEncryptor;
    public EncriptadorServiceImpl(){
    }
    @Override
    public String encriptar(String texto_encriptar, String cifrado) {
        standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setPassword(cifrado);
        return standardPBEStringEncryptor.encrypt(texto_encriptar);
    }

    @Override
    public String desencriptar(String texto_desencriptar, String cifrado) {
        standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setPassword(cifrado);
        return standardPBEStringEncryptor.decrypt(texto_desencriptar);
    }
}
