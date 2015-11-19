using System;
using System.Collections.Generic;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Com.Jerometian.JsonWebSite.Models;
using Com.Jerometian.Infrastructure;

namespace Com.Jerometian.JsonWebSite
{
    public partial class _Default : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            List<Book> books = PopulateBookData();
            string jsonString = NewtonJsonHelper.SerializeObject(books);
            Response.Write(jsonString);
        }

        private List<Book> PopulateBookData()
        {
            Book book = new Book();
            List<Book> books = new List<Book>();
            books.Add(new Book
            {
                Id = 1,
                Name = "Angry Birds",
                Version = "5.5",
                PublishDateTime = new DateTime(2015,11,12)
            });

            books.Add(new Book
            {
                Id = 2,
                Name = "Angry Birds",
                Version = "5.5",
                PublishDateTime = new DateTime(2015,11,12 ,10, 30,29)
            });


            books.Add(new Book
            {
                Id = 3,
                Name = "Angry Birds",
                Version = "5.5",
                PublishDateTime = new DateTime(2015, 11, 12, 10, 30,58)
            });
            return books;
        }
    }
}