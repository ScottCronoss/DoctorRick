/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Doctor;
import org.hibernate.Session;

/**BEAN CRUD (Create Read Update Delete)
 *DAO (Data Access Object)
 * @author NoelCS
 */
@ManagedBean
@ViewScoped
public class DoctorControl {
    private List<Doctor> listaDoctores;
    private Doctor doctor;
    
    public DoctorControl() {
        doctor = new Doctor();
    }
    
    public List<Doctor> getListaDoctores() throws Throwable {
        try (ConexionControl conn = new ConexionControl()) {
            Session s = conn.getSesionHibernate();
            listaDoctores = s.createCriteria(Doctor.class).list();
        }
        return listaDoctores;
    }
    
    public void setListaDoctores(List<Doctor> listaDoctores) {
        this.listaDoctores = listaDoctores;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    public void vaciaDoctor() {
        doctor = new Doctor();
    }

    public void eliminaDoctor() throws Throwable {
        try (ConexionControl conn = new ConexionControl()) {
            Session s = conn.getSesionHibernate();
            s.delete(doctor);
            conn.getTransaccion().commit();
        }
    }

    public void creaDoctor() throws Throwable {
        try (ConexionControl conn = new ConexionControl()) {
            Session s = conn.getSesionHibernate();
            s.save(doctor);
            conn.getTransaccion().commit();
        }
    }

    public void modificaDoctor() throws Throwable {
        try (ConexionControl conn = new ConexionControl()) {
            Session s = conn.getSesionHibernate();
            s.update(doctor);
            conn.getTransaccion().commit();
        }
    }
}
