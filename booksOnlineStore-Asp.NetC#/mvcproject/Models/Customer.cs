using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace mvcproject.Models
{
    public class Customer
    {     
        [Required(ErrorMessage ="First Name is required")]
        [StringLength(20, MinimumLength = 2, ErrorMessage = "First Name must be at least 2 characters")]
        public string customerFirstName { get; set; }

        [Required(ErrorMessage = "Last Name is required")]
        [StringLength(20, MinimumLength = 2, ErrorMessage = "Last Name must be at least 2 characters")]
        public string customerLastName { get; set; }

        [Key]
        [Required(ErrorMessage = "User Id is required")]
        [RegularExpression("^[0-9]{9}$", ErrorMessage = "User Id must be 9 numbers")]
        public string customerId { get; set; }

        [Required(ErrorMessage = "Password is required")]
        [RegularExpression("^[0-9a-zA-Z]{6}$", ErrorMessage = "Password must be 6 numbers and/or letters")]
        public string customerPassword { get; set; }
    }
}