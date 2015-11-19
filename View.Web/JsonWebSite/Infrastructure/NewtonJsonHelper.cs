using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
namespace Com.Jerometian.Infrastructure
{
    public class NewtonJsonHelper
    {
        public static string SerializeObject(object data)
        {
            return JsonConvert.SerializeObject(data);
        }
    }
}
