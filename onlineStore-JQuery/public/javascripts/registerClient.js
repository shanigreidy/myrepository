$(document).ready(function () {
    $("#registerForm").on("submit", registerUser);

    function registerUser(event) 
    {
        event.preventDefault();
        var userName = $("#register_user_email").val();
        var userPassword = $("#register_user_password").val();
        var confirmUserPassword = $("#register_confirm_user_password").val(); 

        $.get('/users/',function(data){
            if(data)
            {
                 if (!isUserExists(userName, data)) 
                 {
                     if (userPassword === confirmUserPassword) 
                     {
                           var userRegistrationData = {
                                                         name: userName,
                                                         password: userPassword
                                                      };

                            $.post("/users/register", userRegistrationData);
                            window.location = "/";
                     }
                     else
                     {
                        alert('password and confirm password not match');
                     }
                 }
                 else
                 {
                    alert('user name already exists');
                 }

            }

        });
    }

    function isUserExists (userName, usersArr)
    {
        for(var i=0; i<usersArr.length; i++)
        {
            if(usersArr[i].name === userName)
            {
                return true;
            }
        }

        return false;

    }
});