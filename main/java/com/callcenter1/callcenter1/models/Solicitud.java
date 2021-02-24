package com.callcenter1.callcenter1.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;
import java.util.Timer;
import org.greenrobot.greendao.DaoException;
import org.json.JSONException;
import org.json.JSONObject;

@Entity(nameInDb = "Solicitud")
public class Solicitud {

        @Id(autoincrement = true)
        private Long id;

        @Property(nameInDb = "estado")
        private String estado;

        @Property(nameInDb ="tipoSolicitud")
        private String tipoSolicitud;

        @Property(nameInDb = "hora")
        private String hora;

        @Property(nameInDb = "detalle")
        private String detalle;

        //relacion con cliente
        private Long clientId;


        //////////////////

    @ToMany(referencedJoinProperty = "solicitudId")
    private List<Asignacion> asignaciones;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1526481261)
    private transient SolicitudDao myDao;

    @Generated(hash = 580477597)
    public Solicitud(Long id, String estado, String tipoSolicitud, String hora, String detalle, Long clientId) {
        this.id = id;
        this.estado = estado;
        this.tipoSolicitud = tipoSolicitud;
        this.hora = hora;
        this.detalle = detalle;
        this.clientId = clientId;
    }


    public Solicitud() {


    }

    public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public String getHora() {
            return hora;
        }

        public void setHora(String hora) {
            this.hora = hora;
        }

        public String getDetalle() {
            return detalle;
        }

        public void setDetalle(String detalle) {
            this.detalle = detalle;
        }

        public String getTipoSolicitud() {
            return tipoSolicitud;
        }

        public void setTipoSolicitud(String tipoSolicitud) {
            this.tipoSolicitud = tipoSolicitud;
        }

        public Long getClientId() {
            return this.clientId;
        }

        public void setClientId(Long clientId) {
            this.clientId = clientId;
        }

        /**
         * To-many relationship, resolved on first access (and after reset).
         * Changes to to-many relations are not persisted, make changes to the target entity.
         */
        @Generated(hash = 1597103658)
        public List<Asignacion> getAsignaciones() {
            if (asignaciones == null) {
                final DaoSession daoSession = this.daoSession;
                if (daoSession == null) {
                    throw new DaoException("Entity is detached from DAO context");
                }
                AsignacionDao targetDao = daoSession.getAsignacionDao();
                List<Asignacion> asignacionesNew = targetDao._querySolicitud_Asignaciones(id);
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

        public void parseSolicitud(int id, JSONObject object){
        try {

            this.setId(Long.valueOf(id));
            this.setEstado(object.getString("estado"));
            this.setTipoSolicitud(object.getString("tipo"));
            this.setDetalle(object.getString("detalle"));
            this.setHora(object.getString("hora"));////////
            this.setClientId(object.getLong("cliente"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1893981349)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSolicitudDao() : null;
    }


}