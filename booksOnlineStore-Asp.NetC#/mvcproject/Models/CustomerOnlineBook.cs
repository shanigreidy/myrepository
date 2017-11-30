using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace mvcproject.Models
{
    public class CustomerOnlineBook
    {
        [Key]
        public string customerId { get; set; }
        public string onlineBookId { get; set; }
    }
}