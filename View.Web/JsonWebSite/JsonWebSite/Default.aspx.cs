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
            GetObject();
            List<Book> books = PopulateBookData();
            string jsonString = NewtonJsonHelper.SerializeObject(books);
            Response.Write(jsonString);
        }
        private void GetObject()
        {
            string strJson = @"[{  
        'id':1,  
        'text':'All Object',  
        'iconCls':'',   
        'children':[{  
            'id':11,  
            'text':'Key attributes',  
            'children':[{  
                'id':111,  
                'text':'Item1'  
            },{  
                'id':112,  
                'text':'Item2'  
            },{  
                'id':113,  
                'text':'Item3'  
            }]  
        },{  
            'id':12,  
            'text':'Service table 1',  
            'state':'closed',  
            'children':[{  
                'id':121,  
                'text':'Service 1'  
            },{  
                'id':122,  
                'text':'Service 2'  
            },{  
                'id':123,  
                'text':'Service 3'  
            }]  
        },{  
            'id':13,  
            'text':'Service table 2',  
            'state':'closed',  
            'children':[{  
                'id':131,  
                'text':'Service 1'  
            },{  
                'id':132,  
                'text':'Service 2'  
            },{  
                'id':133,  
                'text':'Service 3'  
            }]  
        },{  
            'id':14,  
            'text':'Service table 3'  
        }]  
    }]";
            object res =  NewtonJsonHelper.DeserializeObject(strJson);
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