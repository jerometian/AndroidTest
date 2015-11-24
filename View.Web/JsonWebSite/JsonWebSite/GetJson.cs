using System;
using System.Web;
using Com.Jerometian.JsonWebSite.Models;
using Com.Jerometian.Infrastructure;
using System.Collections.Generic;
namespace Com.Jerometian.JsonWebSite
{
    public class GetJson : IHttpHandler
    {
        /// <summary>
        /// You will need to configure this handler in the Web.config file of your 
        /// web and register it with IIS before being able to use it. For more information
        /// see the following link: http://go.microsoft.com/?linkid=8101007
        /// </summary>
        #region IHttpHandler Members

        public bool IsReusable
        {
            // Return false in case your Managed Handler cannot be reused for another request.
            // Usually this would be false in case you have some state information preserved per request.
            get { return true; }
        }

        public void ProcessRequest(HttpContext context)
        {
            List<Book> books = PopulateBookData();
            string jsonString = NewtonJsonHelper.SerializeObject(books);

            context.Response.ContentType = "application/json";
            context.Response.ContentEncoding = System.Text.Encoding.UTF8;
            context.Response.Write(jsonString);
        }



        #endregion


        private List<Book> PopulateBookData()
        {
            Book book = new Book();
            List<Book> books = new List<Book>();
            books.Add(new Book
            {
                Id = 1,
                Name = "Angry Birds",
                Version = "5.5",
                PublishDateTime = new DateTime(2015, 11, 12)
            });

            books.Add(new Book
            {
                Id = 2,
                Name = "Angry Birds",
                Version = "5.5",
                PublishDateTime = new DateTime(2015, 11, 12, 10, 30, 29)
            });


            books.Add(new Book
            {
                Id = 3,
                Name = "Angry Birds",
                Version = "5.5",
                PublishDateTime = new DateTime(2015, 11, 12, 10, 30, 58)
            });
            return books;
        }
    }
}
