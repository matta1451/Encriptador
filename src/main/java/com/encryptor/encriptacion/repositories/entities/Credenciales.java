package com.encryptor.encriptacion.repositories.entities;

import java.io.Serializable;

public class Credenciales implements Serializable {
    private String clave;
    private String cifrado;

    public Credenciales(String clave, String cifrado) {
        this.clave = clave;
        this.cifrado = cifrado;
    }
    public Credenciales(){}

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCifrado() {
        return cifrado;
    }

    public void setCifrado(String cifrado) {
        this.cifrado = cifrado;
    }
}
