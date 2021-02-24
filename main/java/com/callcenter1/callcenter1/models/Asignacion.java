package com.callcenter1.callcenter1.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;
import java.util.Timer;

@Entity(nameInDb = "Asignacion")
public class Asignacion {

    @Id
    private Long id;
    @Property(nameInDb = "horaInicio")
    private String horaInicio;
    @Property(nameInDb = "horaFin")
    private String horaFin;

    @Property(nameInDb ="materiales")
    private String materiales;

    private Long solicitudId;
    private Long empleadoId;

    public Asignacion() {
    }

    public Asignacion(String horaInicio, String horaFin, String materiales, Long solicitudId, Long empleadoId) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;

        this.materiales = materiales;
    }

    @Generated(hash = 138328041)
    public Asignacion(Long id, String horaInicio, String horaFin, String materiales, Long solicitudId, Long empleadoId) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.materiales = materiales;
        this.solicitudId = solicitudId;
        this.empleadoId = empleadoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }



    public String getMateriales() {
        return materiales;
    }

    public void setMateriales(String materiales) {
        this.materiales = materiales;
    }

    public Long getSolicitudId() {
        return this.solicitudId;
    }

    public void setSolicitudId(Long solicitudId) {
        this.solicitudId = solicitudId;
    }

    public Long getEmpleadoId() {
        return this.empleadoId;
    }

    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }


}
