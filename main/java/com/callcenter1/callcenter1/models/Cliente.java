package com.callcenter1.callcenter1.models;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.DaoException;
import org.json.JSONException;
import org.json.JSONObject;

@Entity(nameInDb = "Cliente")

public class Cliente {


//
    @Id
    private Long id;

    @Property(nameInDb = "ci")
    private int ci;

    @Property(nameInDb = "nombre")
    public String nombre;

    @Property(nameInDb = "apellido")
    private String apellido;

    @Property(nameInDb = "latitud")
    private double latitud;

    @Property(nameInDb ="longitud")
    private double longitud;

    @Property(nameInDb = "direccion")
    public String direccion;

    @Property(nameInDb = "nroContrato")
    private String nroContrato;

    @Property(nameInDb = "telefono")
    private String telefono;

    @ToMany(referencedJoinProperty = "clientId")
    private List<Solicitud> solicitudes;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 356477667)
    private transient ClienteDao myDao;

    public Cliente( int ci, String nombre, double latitud, double longitud, String direccion, String nroContrato,
                    String telefono) {

        this.ci = ci;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
        this.nroContrato = nroContrato;
        this.telefono = telefono;
    }

    @Generated(hash = 1309435506)
    public Cliente(Long id, int ci, String nombre, String apellido, double latitud, double longitud, String direccion,
            String nroContrato, String telefono) {
        this.id = id;
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
        this.nroContrato = nroContrato;
        this.telefono = telefono;
    }

    @Generated(hash = 1805939709)
    public Cliente() {
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

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNroContrato() {
        return nroContrato;
    }

    public void setNroContrato(String nroContrato) {
        this.nroContrato = nroContrato;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 2051559174)
    public List<Solicitud> getSolicitudes() {
        if (solicitudes == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SolicitudDao targetDao = daoSession.getSolicitudDao();
            List<Solicitud> solicitudesNew = targetDao._queryCliente_Solicitudes(id);
            synchronized (this) {
                if (solicitudes == null) {
                    solicitudes = solicitudesNew;
                }
            }
        }
        return solicitudes;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1882533641)
    public synchronized void resetSolicitudes() {
        solicitudes = null;
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

    public void parseCliente(int id, JSONObject object){
        try {

            this.setId(Long.valueOf(id));
            this.setCi(Integer.parseInt(object.getString("ci")));
            this.setNombre(object.getString("nombre"));
            this.setApellido(object.getString("apellido"));
            this.setLatitud(Double.parseDouble(object.getString("latitud")));
            this.setLongitud(Double.parseDouble(object.getString("longitud")));
            this.setDireccion(object.getString("direccion"));
            this.setNroContrato(String.valueOf(object.getInt("nro_contrato")));
            this.setTelefono(object.getString("telefono"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 48169481)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getClienteDao() : null;
    }
}


