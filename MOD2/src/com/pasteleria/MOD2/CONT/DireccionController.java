package com.pasteleria.MOD2.CONT;

import com.pasteleria.MOD2.MOD.EntidadDireccion;

import javax.persistence.*;
import java.util.List;

public class DireccionController {
    @PersistenceContext(unitName = "bdproveedores")
    private EntityManager em;

    public DireccionController(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdproveedores");
        em = emf.createEntityManager();
    }

    public boolean AgregarDireccion(EntidadDireccion direccion){
        EntityTransaction tx = null;
        try{
            tx = em.getTransaction();
            tx.begin();
            em.persist(direccion);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            System.out.println(e);
            return false;
        }
    }

    public boolean ModificarDireccion(EntidadDireccion direccion){
        EntityTransaction tx = null;
        try{
            tx = em.getTransaction();
            tx.begin();
            em.merge(direccion);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            System.out.println(e);
            return false;
        }
    }

    public boolean EliminarDireccion(EntidadDireccion direccion){
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.remove(em.merge(direccion));
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            System.out.println(e);
            return false;
        }
    }

    public List<EntidadDireccion> MostrarTodas(){
        try{
            List<EntidadDireccion> direccion = em.createNamedQuery("Direccion.MostrarTodas").getResultList();
            return direccion;
        }catch (Exception e){
            System.out.println("Error al cargar las direcciones de los proveedores");
            return null;
        }
    }

    public List<Integer> CrearListaDireccionesID(){
        try{
            List<Integer> direccion = em.createNamedQuery("Direccion.ListarID").getResultList();
            return direccion;
        }catch (Exception e){
            System.out.println("Error al traer el ID de las direcciones de proveedores");
            return null;
        }
    }

    public EntidadDireccion BuscarID(int id){
        try{
            EntidadDireccion direccion = (EntidadDireccion) em.createNamedQuery("Direccion.BuscarID").setParameter("id", id).getSingleResult();
            return direccion;
        }catch (Exception e){
            return null;
        }
    }
}
