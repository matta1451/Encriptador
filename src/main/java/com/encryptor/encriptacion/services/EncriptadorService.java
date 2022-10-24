package com.encryptor.encriptacion.services;

public interface EncriptadorService {
    String encriptar(String texto_encriptar, String cifrado);
    String desencriptar(String texto_desencriptar, String cifrado);
}
