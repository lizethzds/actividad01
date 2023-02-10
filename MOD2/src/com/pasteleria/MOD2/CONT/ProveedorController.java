package com.pasteleria.MOD2.CONT;

import com.pasteleria.MOD2.MOD.EntidadProveedor;

import javax.persistence.*;
import java.util.List;

public class ProveedorController {
    @PersistenceContext(unitName = "bdproveedores")
    private EntityManager am;

    public ProveedorController(){
        EntityManagerFactory amf = Persistence.createEntityManagerFactory("bdproveedores");
        am = amf.createEntityManager();
    }

    public boolean AgregarProveedor(EntidadProveedor proveedor){
        EntityTransaction tx = null;
        try{
            tx = am.getTransaction();
            tx.begin();
            am.persist(proveedor);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            System.out.println(e);
            return false;
        }
    }

    public boolean ModificarProveedor(EntidadProveedor proveedor){
        EntityTransaction tx = null;
        try{
            tx = am.getTransaction();
            tx.begin();
            am.merge(proveedor);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            System.out.println(e);
            return false;
        }
    }

    public boolean EliminarProveedor(EntidadProveedor proveedor){
        EntityTransaction tx = null;
        try {
            tx = am.getTransaction();
            tx.begin();
            am.remove(am.merge(proveedor));
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            System.out.println(e);
            return false;
        }
    }

    public List<EntidadProveedor> ListarTodos(){
        try{
            List<EntidadProveedor> proveedores = am.createNamedQuery("Proveedor.MostrarTodos").getResultList();
            return proveedores;
        }catch (Exception e){
            System.out.println("Error al cargar los proveedores [LSTD]");
            return null;
        }
    }

    public List<Integer> CrearListaID(){
        try{
            List<Integer> proveedores = am.createNamedQuery("Proveedor.CrearListaID").getResultList();
            return proveedores;
        }catch (Exception e){
            System.out.println("Error al crear la lista de ID de proveedor [CLID]");
            return null;
        }
    }

    /*public List<EntidadProveedor> CrearListaNombres(){
        try{
            List<EntidadProveedor> proveedores = am.createNamedQuery("Proveedor.CrearListaNombres").getResultList();
            return proveedores;
        }catch (Exception e){
            System.out.println("Error al cargar la lista de nombres de proveedores [CLN]");
            return null;
        }
    }*/

    public EntidadProveedor BuscarPorID(int id){
        try{
            EntidadProveedor proveedor = (EntidadProveedor) am.createNamedQuery("Proveedor.BuscarID").setParameter("id", id).getSingleResult();
            return proveedor;
        }catch (Exception e){
            System.out.println("Error al traer el proveedor buscado por ID [BID]");
            return null;
        }
    }
}
