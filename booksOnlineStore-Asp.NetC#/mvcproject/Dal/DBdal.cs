using mvcproject.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace mvcproject.Dal
{
    public class DBdal : DbContext
    {
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.Entity<Customer>().ToTable("tblCustomers");
            modelBuilder.Entity<OnlineBook>().ToTable("tblOnlineBooks");
            modelBuilder.Entity<CustomerOnlineBook>().ToTable("tblCustomerOnlineBooks");
        }
        public DbSet<Customer> customers { get; set; }
        public DbSet<OnlineBook> onlineBooks { get; set; }
        public DbSet<CustomerOnlineBook> customerOnlineBooks { get; set; }
    }
}