package com.example.productos.controller;

import com.example.productos.model.Usuario;
import com.example.productos.service.UsuarioService;
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
public class UsuarioBean implements Serializable {

    @Inject
    private UsuarioService usuarioService;

    private List<Usuario> usuarios;
    private Usuario selectedUsuario;

    @PostConstruct
    public void init() {
        this.usuarios = usuarioService.findAll();
    }

    public void openNew() {
        this.selectedUsuario = new Usuario();
    }

    public void saveUsuario() {
        if (this.selectedUsuario.getId() == null) {
            // New user
            usuarioService.save(this.selectedUsuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario Añadido"));
        } else {
            // Existing user
            usuarioService.save(this.selectedUsuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario Actualizado"));
        }
        this.usuarios = usuarioService.findAll(); // Refresh list
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("usuarioForm:usuariosTable");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("messages");
    }

    public void deleteUsuario() {
        if (this.selectedUsuario != null && this.selectedUsuario.getId() != null) {
            usuarioService.deleteById(this.selectedUsuario.getId());
            this.usuarios.remove(this.selectedUsuario); // Remove from list
            this.selectedUsuario = null; // Clear selection
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Eliminado", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No hay usuario seleccionado para eliminar."));
        }
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("usuarioForm:usuariosTable");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("messages");
    }

    // Getters and Setters
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }
}
