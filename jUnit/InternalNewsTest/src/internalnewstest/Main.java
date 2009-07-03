package internalnewstest;

import fr.unice.i3s.modalis.jseduite.technical.news.internal.News;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Steve Colombié
 */
public class Main {
    public static XMLGregorianCalendar toXmlCalendar(Date d) {
        try {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(d);
            DatatypeFactory factory = DatatypeFactory.newInstance();
            return factory.newXMLGregorianCalendar(calendar);
        } catch(Exception e) {
            return null;
        }
   }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        InternalNewsCRUDProxy crud = new InternalNewsCRUDProxy();
        int lastId = 0;

        // Create (see result in the database)
        {
            System.out.print("\n====\nCREATE\n====\n");

            News n1 = new News();
            n1.setTarget("1");
            n1.setTitle("Titre 1");
            n1.setAuthor("Steve Colombié");
            n1.setStart(toXmlCalendar(new Date()));
            n1.setEnd(toXmlCalendar(new Date()));
            n1.setContent("Content of the first news");
            lastId = crud.create(n1);

            System.out.print("News "+lastId+" created.\n");

            News n2 = new News();
            n2.setTarget("2");
            n2.setTitle("Titre 2");
            n2.setAuthor("Karim Matrah");
            n2.setStart(toXmlCalendar(new Date()));
            n2.setEnd(toXmlCalendar(new Date()));
            n2.setContent("Content of the second news");
            lastId = crud.create(n2);

            System.out.print("News "+lastId+" created.\n");


            News n3 = new News();
            n3.setTarget("3");
            n3.setTitle("Titre 3");
            n3.setAuthor("Xavier Gentile");
            n3.setStart(toXmlCalendar(new Date()));
            n3.setEnd(toXmlCalendar(new Date()));
            n3.setContent("Content of the third news");
            lastId = crud.create(n3);

            System.out.print("News "+lastId+" created.\n");
        }

        // Read
        {
            int id = 2;
            News news = crud.read(id);

            String target = news.getTarget();
            String title = news.getTitle();
            String author = news.getAuthor();
            String content = news.getContent();
            XMLGregorianCalendar start = news.getStart();
            XMLGregorianCalendar end = news.getEnd();

            System.out.print("\n====\nREAD\n====\n");
            System.out.print("News : "+id+"\n");
            System.out.print("Target : "+target+"\n");
            System.out.print("Title : "+title+"\n");
            System.out.print("Author : "+author+"\n");
            System.out.print("Start : "+start.toString()+"\n");
            System.out.print("End : "+end.toString()+"\n");
            System.out.print("Content : "+content+"\n");
        }

        //Update
        {
            News n1 = crud.read(lastId);
            n1.setContent("The content has changed!!");
            crud.update(n1);

            System.out.print("\n====\nUPDATE\n====\n");
            System.out.print("News "+lastId+" updated.\n");
        }

        //Delete
        {
            News n1 = crud.read(lastId-1);
            crud.delete(n1);

            System.out.print("\n====\nDELETE\n====\n");
            System.out.print("News "+(lastId-1)+" deleted.\n");
        }

        // Finder
        {
            InternalNewsFinderProxy finder = new InternalNewsFinderProxy();

            int id = 1;
            News news = finder.findInternalNewsById(id);
            String target = news.getTarget();
            String title = news.getTitle();
            String author = news.getAuthor();
            String content = news.getContent();
            XMLGregorianCalendar start = news.getStart();
            XMLGregorianCalendar end = news.getEnd();


            int newsCard = finder.getAllInternalNewsReferences().size();

            System.out.print("====\nFINDER\n====\n");
            System.out.print("News : "+id+"/"+newsCard+"\n");
            System.out.print("Target : "+target+"\n");
            System.out.print("Title : "+title+"\n");
            System.out.print("Author : "+author+"\n");
            System.out.print("Start : "+start.toString()+"\n");
            System.out.print("End : "+end.toString()+"\n");
            System.out.print("Content : "+content+"\n");
        }
    }
}
