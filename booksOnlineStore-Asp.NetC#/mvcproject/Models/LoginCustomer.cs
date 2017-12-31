using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace mvcproject.Models
{
    public class LoginCustomer
    {
        [Required(ErrorMessage = "User Id is required")]
        [RegularExpression("^[0-9]{9}$", ErrorMessage = "User Id must be 9 numbers")]
        public string customerId { get; set; }

        [Required(ErrorMessage = "Password is required")]
        [RegularExpression("^[0-9a-zA-Z]{6}$", ErrorMessage = "Password must be 6 numbers and letters")]
        public string customerPassword { get; set; }
    }
}