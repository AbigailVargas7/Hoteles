/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.modelo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Usuario
 */
public class TipoHabitacion implements Serializable{
    private static final long serialVersionUID = 771223652324025278L;
    private String tipoHabitacion;
    final static TipoHabitacion[] arrTipos = {new TipoHabitacion("Matrimonial"), new TipoHabitacion("Suite"), new TipoHabitacion("Doble"),new TipoHabitacion("Triple")};

    /**
     *
     */
    public static List<TipoHabitacion> tiposH = Arrays.asList(arrTipos);

    /**
     *
     * @param string
     */
    public TipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    /**
     *
     * @param tipoHabitacion
     */
    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }
    
    
    @Override
    public String toString() {
        return tipoHabitacion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.tipoHabitacion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoHabitacion other = (TipoHabitacion) obj;
        if (!Objects.equals(this.tipoHabitacion, other.tipoHabitacion)) {
            return false;
        }
        return true;
    }

    
}
