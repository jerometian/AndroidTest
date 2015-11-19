using System;
using System.Collections.Generic;
using System.Web;

namespace Com.Jerometian.JsonWebSite.Models
{
    public class Book
    {
        public int Id { get; set; }

        public string Version { get; set; }

        public string Name { get; set; }

        public DateTime PublishDateTime { get; set; }

    }
}