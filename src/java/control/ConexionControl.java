package control;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author NoelCS
 */
public class ConexionControl implements AutoCloseable{
    private Session sesion=null;
    private Transaction transaccion=null;
    
    public Session getSesionHibernate() throws Exception{
        if(sesion==null){
            sesion = HibernateUtil.getSessionFactory().openSession();
            transaccion = sesion.beginTransaction();
        }
        return sesion;
    }

    public Transaction getTransaccion() {
        return transaccion;
    }
    
    @Override
    public void close() throws Exception {
        if(sesion !=null){
            if(sesion.isOpen()){
                if(transaccion != null){
                    if(transaccion.isActive()){
                        try{
                            transaccion.commit();
                        }
                        catch(Throwable t){
                            transaccion.rollback();
                        }
                    }
                    transaccion = null;
                }
                sesion.close();
            }
            sesion = null;
        }
        System.out.println("Recursos Cerrados");
    }
}
