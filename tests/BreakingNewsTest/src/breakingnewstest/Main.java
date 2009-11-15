package breakingnewstest;

import fr.unice.i3s.modalis.jseduite.technical.news.breaking.BreakNew;
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

        // Finder
        {
            BreakingNewsFinderProxy finder = new BreakingNewsFinderProxy();

            int id = 1;
            BreakNew breakNew = finder.findBreakNewById(id);
            String author = breakNew.getAuthor();
            String content = breakNew.getContent();
            XMLGregorianCalendar stamp = breakNew.getStamp();

            int newsCard = finder.getAllBreakingNewsReferences().size();

            System.out.print("====\nFINDER\n====\n");
            System.out.print("Break New : "+id+"/"+newsCard+"\n");
            System.out.print("Author : "+author+"\n");
            System.out.print("Date : "+stamp.toString()+"\n");
            System.out.print("Content : "+content+"\n");
        }

        BreakingNewsCRUDProxy crud = new BreakingNewsCRUDProxy();
        int lastId = 0;

        // Create (see result in the database)
        {
            System.out.print("\n====\nCREATE\n====\n");

            BreakNew bn1 = new BreakNew();
            bn1.setAuthor("Steve Colombié");
            bn1.setStamp(toXmlCalendar(new Date()));
            bn1.setContent("Content of the first news");
            lastId = crud.create(bn1);

            System.out.print("News "+lastId+" created.\n");

            BreakNew bn2 = new BreakNew();
            bn2.setAuthor("Karim Matrah");
            bn2.setStamp(toXmlCalendar(new Date()));
            bn2.setContent("Content of the second news");
            lastId = crud.create(bn2);

            System.out.print("News "+lastId+" created.\n");
        }

        // Read
        {
            int id = 3;
            BreakNew breakNew = crud.read(id);

            String author = breakNew.getAuthor();
            String content = breakNew.getContent();
            XMLGregorianCalendar stamp = breakNew.getStamp();

            System.out.print("\n====\nREAD\n====\n");
            System.out.print("Break New : "+id+"\n");
            System.out.print("Author : "+author+"\n");
            System.out.print("Date : "+stamp.toString()+"\n");
            System.out.print("Content : "+content+"\n");
        }

        //Update
        {
            BreakNew bn1 = crud.read(lastId);
            bn1.setContent("The content has changed!!");
            crud.update(bn1);

            System.out.print("\n====\nUPDATE\n====\n");
            System.out.print("News "+lastId+" updated.\n");
        }

        //Delete
        {
            BreakNew bn1 = crud.read(lastId-1);
            crud.delete(bn1);

            System.out.print("\n====\nDELETE\n====\n");
            System.out.print("News "+(lastId-1)+" deleted.\n");
        }
    }

}
