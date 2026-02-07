package com.example.productos.controller;

import com.example.productos.model.Producto;
import com.example.productos.service.ProductoService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Named
@ViewScoped
public class ProductoBean implements Serializable {

    @Inject
    private ProductoService productoService;

    private List<Producto> productos;
    private Producto selectedProducto;

    @PostConstruct
    public void init() {
        this.productos = productoService.findAll();
    }

    public void openNew() {
        this.selectedProducto = new Producto();
    }

    public void saveProducto() {
        if (this.selectedProducto.getId() == null) {
            // New product
            productoService.save(this.selectedProducto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Añadido"));
        } else {
            // Existing product
            productoService.save(this.selectedProducto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Actualizado"));
        }
        this.productos = productoService.findAll(); // Refresh list
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("productoForm:productosTable");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("messages");
    }

    public void deleteProducto() {
        if (this.selectedProducto != null && this.selectedProducto.getId() != null) {
            productoService.deleteById(this.selectedProducto.getId());
            this.productos.remove(this.selectedProducto); // Remove from list
            this.selectedProducto = null; // Clear selection
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto Eliminado", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No hay producto seleccionado para eliminar."));
        }
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("productoForm:productosTable");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("messages");
    }

    // Getters and Setters
    public List<Producto> getProductos() {
        return productos;
    }

    public Producto getSelectedProducto() {
        return selectedProducto;
    }

    public void setSelectedProducto(Producto selectedProducto) {
        this.selectedProducto = selectedProducto;
    }
}
