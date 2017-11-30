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
        [StringLength(20,MinimumLength = 2)]
        public string customerFirstName { get; set; }

        [Required(ErrorMessage = "Last Name is required")]
        [StringLength(20,MinimumLength = 2)]
        public string customerLastName { get; set; }

        [Key]
        [Required(ErrorMessage = "Id is required")]
        [RegularExpression("^[0-9]{9}$", ErrorMessage = "Id must be 9 numbers")]
        public string customerId { get; set; }

        [Required(ErrorMessage = "Password is required")]
        [RegularExpression("^[0-9a-zA-Z]{6}$", ErrorMessage = "Password must be 6 numbers and letters")]
        public string customerPassword { get; set; }
    }
}