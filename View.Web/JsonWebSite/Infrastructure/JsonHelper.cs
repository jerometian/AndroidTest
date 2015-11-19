using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.Serialization.Json;
using System.Text;
using System.Threading.Tasks;
using System.Web.Script.Serialization;

namespace Com.Jerometian.Infrastructure
{
    /*
     ASP.NET中的JSON序列化和反序列化还可以使用JavaScriptSerializer，
     * 在System.Web.Script.Serializatioin命名空间下，
     * 需引用System.Web.Extensions.dll.也可以使用JSON.NET.
     */

       


    public static class JsonHelper
    {
        public static T JsonDeserialize<T>(string jsonString)
        {
            DataContractJsonSerializer ser = new DataContractJsonSerializer(typeof(T));
            MemoryStream ms = new MemoryStream(Encoding.UTF8.GetBytes(jsonString));
            T obj = (T)ser.ReadObject(ms);
            return obj;

        }

        public static string JsonSerialize<T>(T obj)
        {
            DataContractJsonSerializer json = new DataContractJsonSerializer(obj.GetType());
            using (MemoryStream stream = new MemoryStream())
            {
                json.WriteObject(stream, obj);
                string jsonString = Encoding.UTF8.GetString(stream.ToArray());
                return jsonString;
            }
        }

        public static List<T> JSONStringToList<T>(this string JsonStr)
        {
            JavaScriptSerializer Serializer = new JavaScriptSerializer();
            List<T> objs = Serializer.Deserialize<List<T>>(JsonStr);
            return objs;
        }

        public static T Deserialize<T>(string json)
        {
            T obj = Activator.CreateInstance<T>();
            using (MemoryStream ms = new MemoryStream(Encoding.UTF8.GetBytes(json)))
            {
                DataContractJsonSerializer serializer = new DataContractJsonSerializer(obj.GetType());
                return (T)serializer.ReadObject(ms);
            }
        }   
    }
}
