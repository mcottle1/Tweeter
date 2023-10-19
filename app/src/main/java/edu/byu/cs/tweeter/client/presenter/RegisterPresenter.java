package edu.byu.cs.tweeter.client.presenter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import edu.byu.cs.tweeter.client.model.services.UserService;

public class RegisterPresenter extends AuthenticatedPresenter {

    public RegisterPresenter(AuthenticatedView view){
        super(view);
    }

    public void register(String firstName, String lastName, String alias, String password, ImageView imageToUpload){
        String image;
        if(imageToUpload.getDrawable() != null) {
            image = convertToByte(imageToUpload);
        }else{
            image = null;
        }
        if(validateRegistration(firstName, lastName, alias, password, image)){
            view.showInfoMessage("Registering...");
            var userService = new UserService();
            userService.Register(firstName, lastName, alias, password, image, new AuthenticateObserver((AuthenticatedView) this.view));
        }
    }

    private boolean validateRegistration(String firstName, String lastName, String alias, String password, String imageToUpload) {
        if (firstName.length() == 0) {
            view.showErrorMessage("First Name cannot be empty.");
            return false;
        }
        if (lastName.length() == 0) {
            view.showErrorMessage("Last Name cannot be empty.");
            return false;
        }
        if (alias.length() == 0) {
            view.showErrorMessage("Alias cannot be empty.");
            return false;
        }
        if (alias.charAt(0) != '@') {
            view.showErrorMessage("Alias must begin with @.");
            return false;
        }
        if (alias.length() < 2) {
            view.showErrorMessage("Alias must contain 1 or more characters after the @.");
            return false;
        }
        if (password.length() == 0) {
            view.showErrorMessage("Password cannot be empty.");
            return false;
        }

        if (imageToUpload == null) {
            view.showErrorMessage("Profile image must be uploaded.");
            return false;
        }
        return true;
    }

    private String convertToByte(ImageView imageToUpload){
        if(imageToUpload != null){
            Bitmap image = ((BitmapDrawable) imageToUpload.getDrawable()).getBitmap();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] imageBytes = bos.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes);
        }
        return null;
    }

}
