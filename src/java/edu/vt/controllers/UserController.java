package edu.vt.controllers;

import edu.vt.EntityBeans.DefaultCar;
import edu.vt.EntityBeans.User;
import edu.vt.EntityBeans.UserPhoto;
import edu.vt.controllers.util.JsfUtil;
import edu.vt.controllers.util.JsfUtil.PersistAction;
import edu.vt.FacadeBeans.UserFacade;
import edu.vt.FacadeBeans.UserPhotoFacade;
import edu.vt.FacadeBeans.DefaultCarFacade;
import edu.vt.globals.Constants;
import edu.vt.globals.Methods;
import edu.vt.globals.Password;
import java.io.IOException;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("userController")
@SessionScoped
public class UserController implements Serializable {

    private String username;
    private String password;
    private String confirmPassword;
    
    private String firstName;
    private String middleName;
    private String lastName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipcode;
    
    private int securityQuestionNumber;
    private String answerToSecurityQuestion;
    
    private String email;
    private Map<String, Object> security_questions;
    
    private String emergencyContactFirstName;
    private String emergencyContactLastName;
    private String emergencyContactEmail;
    private String emergencyContactPhoneNumber;
    private String emergencyContactPhoneCarrier;
    
    @EJB
    private UserFacade userFacade;
    
    @EJB
    private UserPhotoFacade userPhotoFacade;
    
    @EJB
    private DefaultCarFacade defaultCarFacade;
    
    private List<User> items = null;
    private User selected;

    public UserController() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getSecurityQuestionNumber() {
        return securityQuestionNumber;
    }

    public void setSecurityQuestionNumber(int securityQuestionNumber) {
        this.securityQuestionNumber = securityQuestionNumber;
    }

    public String getAnswerToSecurityQuestion() {
        return answerToSecurityQuestion;
    }

    public void setAnswerToSecurityQuestion(String answerToSecurityQuestion) {
        this.answerToSecurityQuestion = answerToSecurityQuestion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Object> getSecurity_questions() {
        if (security_questions == null) {
            /*
            Difference between HashMap and LinkedHashMap:
            HashMap stores key-value pairings in no particular order. 
                Values are retrieved based on their corresponding Keys.
            LinkedHashMap stores and retrieves key-value pairings
                in the order they were put into the map.
             */
            security_questions = new LinkedHashMap<>();

            for (int i = 0; i < Constants.QUESTIONS.length; i++) {
                security_questions.put(Constants.QUESTIONS[i], i);
            }
        }
        return security_questions;
    }

    public void setSecurity_questions(Map<String, Object> security_questions) {
        this.security_questions = security_questions;
    }

    public String getEmergencyContactFirstName() {
        return emergencyContactFirstName;
    }

    public void setEmergencyContactFirstName(String emergencyContactFirstName) {
        this.emergencyContactFirstName = emergencyContactFirstName;
    }

    public String getEmergencyContactLastName() {
        return emergencyContactLastName;
    }

    public void setEmergencyContactLastName(String emergencyContactLastName) {
        this.emergencyContactLastName = emergencyContactLastName;
    }

    public String getEmergencyContactEmail() {
        return emergencyContactEmail;
    }

    public void setEmergencyContactEmail(String emergencyContactEmail) {
        this.emergencyContactEmail = emergencyContactEmail;
    }

    public String getEmergencyContactPhoneNumber() {
        return emergencyContactPhoneNumber;
    }

    public void setEmergencyContactPhoneNumber(String emergencyContactPhoneNumber) {
        this.emergencyContactPhoneNumber = emergencyContactPhoneNumber;
    }

    public String getEmergencyContactPhoneCarrier() {
        return emergencyContactPhoneCarrier;
    }

    public void setEmergencyContactPhoneCarrier(String emergencyContactPhoneCarrier) {
        this.emergencyContactPhoneCarrier = emergencyContactPhoneCarrier;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public UserPhotoFacade getUserPhotoFacade() {
        return userPhotoFacade;
    }

    public void setUserPhotoFacade(UserPhotoFacade userPhotoFacade) {
        this.userPhotoFacade = userPhotoFacade;
    }

    public DefaultCarFacade getDefaultCarFacade() {
        return defaultCarFacade;
    }

    public void setDefaultCarFacade(DefaultCarFacade defaultCarFacade) {
        this.defaultCarFacade = defaultCarFacade;
    }

    public User getSelected() {
        if(selected == null){
            selected = (User)Methods.sessionMap().get("user");
        }
        return selected;
    }

    public void setSelected(User selected) {
        this.selected = selected;
    }
    
    public DefaultCar getUserDefaultCar(){
        return getDefaultCarFacade().findByUserPrimaryKey(this.selected.getId()).get(0);
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
    
    public boolean isLoggedIn(){
        return Methods.sessionMap().get("username") != null;
    }
    
    public String[] listOfStates(){
        return Constants.STATES;
    }
    
    /*
    ****************************************
    Return the Security Question Selected by
    the User at the Time of Account Creation
    ****************************************
     */
    public String getSelectedSecurityQuestion() {
        /*
        'user', the object reference of the signed-in user, was put into the SessionMap
        in the initializeSessionMap() method in LoginManager upon user's sign in.
         */
        User signedInUser = (User) Methods.sessionMap().get("user");

        // Obtain the number of the security question selected by the user
        int questionNumber = signedInUser.getSecurityQuestionNumber();

        // Return the security question corresponding to the question number
        return Constants.QUESTIONS[questionNumber];
    }

    /*
    **********************************************************
    Create User's Account and Redirect to Show the SignIn Page
    **********************************************************
     */
    public String createAccount() {
        
        /*
        ----------------------------------------------------------------
        Password and Confirm Password are validated under 3 tests:
        
        <1> Non-empty (tested with required="true" in JSF page)
        <2> Correct composition satisfying the regex rule.
            (tested with <f:validator validatorId="passwordValidator" />
            in the JSF page)
        <3> Password and Confirm Password must match (tested below)
        ----------------------------------------------------------------
         */
        if (!password.equals(confirmPassword)) {
            Methods.showMessage("Fatal Error", "Unmatched Passwords!",
                    "Password and Confirm Password must Match!");
            return "";
        }

        //--------------------------------------------
        // Password and Confirm Password are Validated
        //--------------------------------------------

        /*
        Redirecting to show a JSF page involves more than one subsequent requests and
        the messages would die from one request to another if not kept in the Flash scope.
        Since we will redirect to show the SignIn page, we invoke preserveMessages().
         */
        Methods.preserveMessages();

        //-----------------------------------------------------------
        // First, check if the entered username is already being used
        //-----------------------------------------------------------
        // Obtain the object reference of a User object with the username entered by the user
        User aUser = getUserFacade().findByUsername(username);

        if (aUser != null) {
            // A user already exists with the username entered by the user
            username = "";
            Methods.showMessage("Fatal Error", "Username Already Exists!", "Please Select a Different One!");
            return "";
        }

        //----------------------------------
        // The entered username is available
        //----------------------------------
        try {
            // Instantiate a new User object
            User newUser = new User();

            /*
             Set the properties of the newly created newUser object with the values
             entered by the user in the AccountCreationForm in CreateAccount.xhtml             
             */
            newUser.setFirstName(firstName);
            newUser.setMiddleName(middleName);
            newUser.setLastName(lastName);
            newUser.setAddress1(address1);
            newUser.setAddress2(address2);
            newUser.setCity(city);
            newUser.setState(state);
            newUser.setZipcode(zipcode);
            newUser.setSecurityQuestionNumber(securityQuestionNumber);
            newUser.setSecurityAnswer(answerToSecurityQuestion);
            newUser.setEmail(email);
            newUser.setEmergencyContactEmail(emergencyContactEmail);
            newUser.setEmergencyContactFirstName(emergencyContactFirstName);
            newUser.setEmergencyContactLastName(emergencyContactLastName);
            newUser.setEmergencyContactPhoneNumber(emergencyContactPhoneNumber);
            newUser.setEmergencyContactPhoneCarrier(emergencyContactPhoneCarrier);
            newUser.setUsername(username);

            //-------------------------------------------------------------------------------------
            // Convert the user-entered String password to a String containing the following parts
            //       "algorithmName":"PBKDF2_ITERATIONS":"hashSize":"salt":"hash"
            // for secure storage and retrieval with Key Stretching to prevent brute-force attacks.
            //-------------------------------------------------------------------------------------
            String parts = Password.createHash(password);
            newUser.setPassword(parts);

            // Create the user in the database
            getUserFacade().create(newUser);

        } catch (EJBException | Password.CannotPerformOperationException ex) {
            username = "";
            Methods.showMessage("Fatal Error", "Something went wrong while creating user's account!",
                    "See: " + ex.getMessage());
            return "";
        }

        Methods.showMessage("Information", "Success!", "User Account is Successfully Created!");

        /*
         The Profile page cannot be shown since the new User has not signed in yet.
         Therefore, we show the Sign In page for the new User to sign in first.
         */
        return "/index.xhtml?faces-redirect=true";
    }

    /*
    ***********************************************************
    Update User's Account and Redirect to Show the Profile Page
    ***********************************************************
     */
    public String updateAccount() {

        // Since we will redirect to show the Profile page, invoke preserveMessages()
        Methods.preserveMessages();

        /*
        'user', the object reference of the signed-in user, was put into the SessionMap
        in the initializeSessionMap() method in LoginManager upon user's sign in.
         */
        User editUser = (User) Methods.sessionMap().get("user");

        try {
            /*
             Set the signed-in user's properties to the values entered by
             the user in the EditAccountProfileForm in EditAccount.xhtml.
             */
            editUser.setFirstName(this.selected.getFirstName());
            editUser.setMiddleName(this.selected.getMiddleName());
            editUser.setLastName(this.selected.getLastName());

            editUser.setAddress1(this.selected.getAddress1());
            editUser.setAddress2(this.selected.getAddress2());
            editUser.setCity(this.selected.getCity());
            editUser.setState(this.selected.getState());
            editUser.setZipcode(this.selected.getZipcode());
            editUser.setEmail(this.selected.getEmail());

            // Store the changes in the database
            getUserFacade().edit(editUser);

            Methods.showMessage("Information", "Success!", "User's Account is Successfully Updated!");

        } catch (EJBException ex) {
            username = "";
            Methods.showMessage("Fatal Error", "Something went wrong while updating user's profile!",
                    "See: " + ex.getMessage());
            return "";
        }

        // Account update is completed, redirect to show the Profile page.
        return "/userAccount/Profile.xhtml?faces-redirect=true";
    }

    /*
    *********************************************
    Process Submitted Answer to Security Question
    *********************************************
     */
    public void securityAnswerSubmit() {

        /*
        'user', the object reference of the signed-in user, was put into the SessionMap
        in the initializeSessionMap() method in LoginManager upon user's sign in.
         */
        User signedInUser = (User) Methods.sessionMap().get("user");

        String actualSecurityAnswer = signedInUser.getSecurityAnswer();
        String enteredSecurityAnswer = getAnswerToSecurityQuestion();

        if (actualSecurityAnswer.equals(enteredSecurityAnswer)) {
            // Answer to the security question is correct; Delete the user's account.
            deleteAccount();

        } else {
            Methods.showMessage("Error", "Answer to the Security Question is Incorrect!", "");
        }
    }

    /*
    *****************************************************************
    Delete User's Account, Logout, and Redirect to Show the Home Page
    *****************************************************************
     */
    public String deleteAccount() {

        // Since we will redirect to show the home page, invoke preserveMessages()
        Methods.preserveMessages();

        /*
        The database primary key of the signed-in User object was put into the SessionMap
        in the initializeSessionMap() method in LoginManager upon user's sign in.
         */
        int userPrimaryKey = (int) Methods.sessionMap().get("user_id");

        try {
            // Delete all of the photo files associated with the signed-in user whose primary key is userPrimaryKey
            deleteAllUserPhotos(userPrimaryKey);

            // Delete the User entity, whose primary key is user_id, from the database
            getUserFacade().deleteUser(userPrimaryKey);

            Methods.showMessage("Information", "Success!", "Your Account is Successfully Deleted!");

        } catch (EJBException ex) {
            username = "";
            Methods.showMessage("Fatal Error", "Something went wrong while deleting user's account!",
                    "See: " + ex.getMessage());
            return "";
        }

        // Execute the logout() method given below
        logout();

        // Redirect to show the index (home) page
        return "/index.xhtml?faces-redirect=true";
    }

    /*
    **********************************************
    Logout User and Redirect to Show the Home Page
    **********************************************
     */
    public void logout() {

        // Clear the signed-in User's session map
        Methods.sessionMap().clear();

        // Reset the signed-in User's properties
        username = password = confirmPassword = "";
        firstName = middleName = lastName = "";
        address1 = address2 = city = state = zipcode = "";
        securityQuestionNumber = 0;
        answerToSecurityQuestion = email = "";
        selected = null;

        // Since we will redirect to show the home page, invoke preserveMessages()
        Methods.preserveMessages();

        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

            // Invalidate the signed-in User's session
            externalContext.invalidateSession();

            /*
            getRequestContextPath() returns the URI of the Web Pages directory of the application.
            Obtain the URI of the index (home) page to redirect to.
             */
            String redirectPageURI = externalContext.getRequestContextPath() + "/index.xhtml";

            // Redirect to show the index (home) page
            externalContext.redirect(redirectPageURI);

            /*
            NOTE: We cannot use: return "/index?faces-redirect=true";
            here because the user's session is invalidated.
             */
        } catch (IOException ex) {
            Methods.showMessage("Fatal Error", "Unable to redirect to the index (home) page!",
                    "See: " + ex.getMessage());
        }
    }

    /*
    *********************************************************
    Return Signed-In User's Thumbnail Photo Relative Filepath
    *********************************************************
     */
    public String userPhoto() {

        /*
        The database primary key of the signed-in User object was put into the SessionMap
        in the initializeSessionMap() method in LoginManager upon user's sign in.
         */
        Integer primaryKey = (Integer) Methods.sessionMap().get("user_id");

        List<UserPhoto> photoList = getUserPhotoFacade().findPhotosByUserPrimaryKey(primaryKey);

        if (photoList.isEmpty()) {
            // No user photo exists. Return defaultUserPhoto.png from the UserPhotoStorage directory.
            return Constants.DEFAULT_PHOTO_RELATIVE_PATH;
        }

        /*
        photoList.get(0) returns the object reference of the first Photo object in the list.
        getThumbnailFileName() message is sent to that Photo object to retrieve its
        thumbnail image file name, e.g., 5_thumbnail.jpeg
         */
        String thumbnailFileName = photoList.get(0).getThumbnailFileName();

        return Constants.PHOTOS_RELATIVE_PATH + thumbnailFileName;
    }

    /*
    *********************************************************
    Delete the photo and thumbnail image files that belong to
    the User object whose database primary key is primaryKey
    *********************************************************
     */
    public void deleteAllUserPhotos(int primaryKey) {

        /*
        Obtain the list of Photo objects that belong to the User whose
        database primary key is userId.
         */
        List<UserPhoto> photoList = getUserPhotoFacade().findPhotosByUserPrimaryKey(primaryKey);

        // photoList.isEmpty implies that the user has never uploaded a photo file
        if (!photoList.isEmpty()) {

            // Obtain the object reference of the first Photo object in the list.
            UserPhoto photo = photoList.get(0);
            try {
                /*
                NOTE: Files and Paths are imported as
                        import java.nio.file.Files;
                        import java.nio.file.Paths;

                 Delete the user's photo if it exists.
                 getFilePath() is given in UserPhoto.java file.
                 */
                Files.deleteIfExists(Paths.get(photo.getPhotoFilePath()));

                /*
                 Delete the user's thumbnail image if it exists.
                 getThumbnailFilePath() is given in UserPhoto.java file.
                 */
                Files.deleteIfExists(Paths.get(photo.getThumbnailFilePath()));

                // Delete the photo file record from the database.
                // UserPhotoFacade inherits the remove() method from AbstractFacade.
                getUserPhotoFacade().remove(photo);

            } catch (IOException ex) {
                Methods.showMessage("Fatal Error",
                        "Something went wrong while deleting user's photo!",
                        "See: " + ex.getMessage());
            }
        }
    }
    
    public void deleteUserDefaultCar(int primaryKey){
        List<DefaultCar> userCar = getDefaultCarFacade().findByUserPrimaryKey(primaryKey);
        
        if(!userCar.isEmpty()){
            DefaultCar car = userCar.get(0);
            getDefaultCarFacade().remove(car);
        }
        
    }

}
