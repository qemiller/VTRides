package edu.vt.controllers;

import edu.vt.EntityBeans.DefaultCar;
import edu.vt.EntityBeans.User;
import edu.vt.controllers.util.JsfUtil;
import edu.vt.controllers.util.JsfUtil.PersistAction;
import edu.vt.FacadeBeans.DefaultCarFacade;
import edu.vt.FacadeBeans.UserFacade;
import edu.vt.globals.Methods;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("defaultCarController")
@SessionScoped
public class DefaultCarController implements Serializable {

    private Integer user_id;
    private String make;
    private String model;
    private String color;
    private String licensePlate;
    @EJB
    private DefaultCarFacade defaultCarFacade;
    @EJB 
    private UserFacade userFacade;
    
    private List<DefaultCar> items = null;
    private DefaultCar selected;

    public DefaultCarController() {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public DefaultCarFacade getDefaultCarFacade() {
        return defaultCarFacade;
    }

    public void setDefaultCarFacade(DefaultCarFacade defaultCarFacade) {
        this.defaultCarFacade = defaultCarFacade;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public DefaultCar getSelected() {
        return selected;
    }

    public void setSelected(DefaultCar selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
    public String setDefaultCar(){
        prepareCreate();
        create();
        User signedInUser = (User)Methods.sessionMap().get("user");
        getDefaultCarFacade().findByUserPrimaryKey(signedInUser.getId()).set(0, selected);
        return "/userAccount/Profile?faces-redirect=true";
    }
    public void removeDefaultCar(){
        User signedInUser = (User)Methods.sessionMap().get("user");
        getDefaultCarFacade().findByUserPrimaryKey(signedInUser.getId()).set(0,null);
        destroy();
    }
    public DefaultCar prepareCreate() {
        selected = new DefaultCar();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DefaultCarCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DefaultCarUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DefaultCarDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DefaultCar> getItems() {
        if (items == null) {
            items = getDefaultCarFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getDefaultCarFacade().edit(selected);
                } else {
                    getDefaultCarFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public DefaultCar getDefaultCar(java.lang.Integer id) {
        return getDefaultCarFacade().find(id);
    }

    public List<DefaultCar> getItemsAvailableSelectMany() {
        return getDefaultCarFacade().findAll();
    }

    public List<DefaultCar> getItemsAvailableSelectOne() {
        return getDefaultCarFacade().findAll();
    }

    @FacesConverter(forClass = DefaultCar.class)
    public static class DefaultCarControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DefaultCarController controller = (DefaultCarController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "defaultCarController");
            return controller.getDefaultCar(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DefaultCar) {
                DefaultCar o = (DefaultCar) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DefaultCar.class.getName()});
                return null;
            }
        }

    }

}
