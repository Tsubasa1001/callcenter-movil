package com.callcenter1.callcenter1.models;

import android.media.Image;
import android.support.annotation.VisibleForTesting;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;


import java.util.List;
import java.util.Timer;
import java.util.function.BinaryOperator;
import org.greenrobot.greendao.DaoException;
import org.json.JSONException;
import org.json.JSONObject;

@Entity(nameInDb = "Empleado")
public class Empleado {

    @Id
    private Long id;

    @Property(nameInDb = "ci")
    private int ci;

    @Property(nameInDb = "nombre")
    private String nombre;

    @Property(nameInDb = "apellido")
    private String apellido;

    @Property(nameInDb = "latitud")
    private Double latitud;

    @Property(nameInDb ="longitud")
    private Double longitud;

    @Property(nameInDb = "huella")
    private String  huella;

    @Property(nameInDb = "nroTrabajos")
    private int  nroTrabajos;

    @Property(nameInDb = "codIdentificacion")
    private int  codIdentificacion;




    @ToMany(referencedJoinProperty = "empleadoId")
    private List<Asignacion> asignaciones;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1897340456)
    private transient EmpleadoDao myDao;


    public Empleado() {
    }

    @Generated(hash = 1855215312)
    public Empleado(Long id, int ci, String nombre, String apellido, Double latitud, Double longitud,
            String huella, int nroTrabajos, int codIdentificacion) {
        this.id = id;
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.latitud = latitud;
        this.longitud = longitud;
        this.huella = huella;
        this.nroTrabajos = nroTrabajos;
        this.codIdentificacion = codIdentificacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getHuella() {
        return huella;
    }

    public void setHuella(String huella) {
        this.huella = huella;
    }


    public int getNroTrabajos() {
        return nroTrabajos;
    }

    public void setNroTrabajos(int nroTrabajos) {
        this.nroTrabajos = nroTrabajos;
    }

    public int getCodIdentificacion(){return codIdentificacion;}

    public void setCodIdentificacion(int codIdentificacion){this.codIdentificacion=codIdentificacion;}
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 793579384)
    public List<Asignacion> getAsignaciones() {
        if (asignaciones == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AsignacionDao targetDao = daoSession.getAsignacionDao();
            List<Asignacion> asignacionesNew = targetDao._queryEmpleado_Asignaciones(id);
            synchronized (this) {
                if (asignaciones == null) {
                    asignaciones = asignacionesNew;
                }
            }
        }
        return asignaciones;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1399911208)
    public synchronized void resetAsignaciones() {
        asignaciones = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public void parseEmpleado(int id, JSONObject object){
        try {

            this.setId(Long.valueOf(id));
            this.setCi(Integer.parseInt(object.getString("ci")));
            this.setNombre(object.getString("nombre"));
            this.setApellido(object.getString("apellido"));
            this.setLatitud(object.getDouble("latitud"));
            this.setLongitud(object.getDouble("longitud"));
            this.setHuella(object.getString("huella"));
            this.setCodIdentificacion(object.getInt("codigo_identificacion"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1943020953)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getEmpleadoDao() : null;
    }
}
