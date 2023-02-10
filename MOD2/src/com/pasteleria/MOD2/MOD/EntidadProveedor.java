package com.pasteleria.MOD2.MOD;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "proveedor", schema = "bdproveedores")

@NamedQueries({
        @NamedQuery(name = "Proveedor.MostrarTodos", query = "select proveedor from EntidadProveedor proveedor order by proveedor.nomEmpresa"),
        @NamedQuery(name = "Proveedor.CrearListaID", query = "select proveedor.idProveedor from EntidadProveedor as proveedor order by proveedor.idProveedor"),
        @NamedQuery(name = "Proveedor.BuscarID", query ="select proveedor from EntidadProveedor proveedor where proveedor.idProveedor = :id"),
        //@NamedQuery(name = "Proveedor.CrearListaNombres", query = "select proveedor.nomEmpresa from EntidadProveedor as proveedor order by proveedor.nomEmpresa"),
})

public class EntidadProveedor {
    //Constructor auxiliar de entidad completa
    public EntidadProveedor(EntidadProveedor entidadProveedor){
        this.nomEmpresa = entidadProveedor.getNomEmpresa();
        this.correo = entidadProveedor.getCorreo();
        this.codigoPostal = entidadProveedor.getCodigoPostal();
        this.telefono = entidadProveedor.getTelefono();
        this.direccionByDireccionIdDireccion = null;
    }

    //Constructor auxiliar por atributos de entidad
    public EntidadProveedor(int idProveedor, String nomEmpresa, String correo, int codigoPostal, String telefono, int direccionIdDireccion){
        this.idProveedor = idProveedor;
        this.nomEmpresa = nomEmpresa;
        this.correo = correo;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
        this.direccionIdDireccion = direccionIdDireccion;
    }

    //Constructor auxiliar por atributos sin ID
    public EntidadProveedor(String nomEmpresa, String correo, int codigoPostal, String telefono, int direccionIdDireccion){
        this.nomEmpresa = nomEmpresa;
        this.correo = correo;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
        this.direccionIdDireccion = direccionIdDireccion;
    }

    //Ya no se que proceda aca
    public EntidadProveedor(int idProveedor, String nomEmpresa, String correo, int codigoPostal, String telefono, EntidadDireccion entidadDireccion){
        this.idProveedor = idProveedor;
        this.nomEmpresa = nomEmpresa;
        this.correo = correo;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
        this.direccionByDireccionIdDireccion = entidadDireccion;
    }

    //Constructor auxiliar por atributos sin ID
    public EntidadProveedor(String nomEmpresa, String correo, int codigoPostal, String telefono, EntidadDireccion entidadDireccion){
        this.nomEmpresa = nomEmpresa;
        this.correo = correo;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
        this.direccionByDireccionIdDireccion = entidadDireccion;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IDProveedor", nullable = false)
    private int idProveedor;
    @Basic
    @Column(name = "NomEmpresa", nullable = false, length = 20)
    private String nomEmpresa;
    @Basic
    @Column(name = "Correo", nullable = false, length = 30)
    private String correo;
    @Basic
    @Column(name = "CodigoPostal", nullable = false)
    private int codigoPostal;
    @Basic
    @Column(name = "Telefono", nullable = false, length = 20)
    private String telefono;
    @Basic
    @Column(name = "Direccion_IDDireccion", nullable = false, insertable = false, updatable = false)
    private int direccionIdDireccion;

    @OneToMany(mappedBy = "proveedorIdProveedor")
    private Collection<EntidadEntrega> entregasByIdProveedor;

    @ManyToOne
    @JoinColumn(name = "Direccion_IDDireccion", referencedColumnName = "IDDIreccion", nullable = true)
    private EntidadDireccion direccionByDireccionIdDireccion;

    public EntidadProveedor() {

    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNomEmpresa() {
        return nomEmpresa;
    }

    public void setNomEmpresa(String nomEmpresa) {
        this.nomEmpresa = nomEmpresa;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getDireccionIdDireccion() {
        return direccionIdDireccion;
    }

    public void setDireccionIdDireccion(int direccionIdDireccion) {
        this.direccionIdDireccion = direccionIdDireccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntidadProveedor that = (EntidadProveedor) o;
        return idProveedor == that.idProveedor && codigoPostal == that.codigoPostal && direccionIdDireccion == that.direccionIdDireccion && Objects.equals(nomEmpresa, that.nomEmpresa) && Objects.equals(correo, that.correo) && Objects.equals(telefono, that.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProveedor, nomEmpresa, correo, codigoPostal, telefono, direccionIdDireccion);
    }

    public Collection<EntidadEntrega> getEntregasByIdProveedor() {
        return entregasByIdProveedor;
    }

    public void setEntregasByIdProveedor(Collection<EntidadEntrega> entregasByIdProveedor) {
        this.entregasByIdProveedor = entregasByIdProveedor;
    }

    public EntidadDireccion getDireccionByDireccionIdDireccion() {
        return direccionByDireccionIdDireccion;
    }

    public void setDireccionByDireccionIdDireccion(EntidadDireccion direccionByDireccionIdDireccion) {
        this.direccionByDireccionIdDireccion = direccionByDireccionIdDireccion;
    }
}
