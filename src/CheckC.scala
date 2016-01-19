import java.io.{InputStreamReader, FileInputStream, PrintWriter, File}
import java.nio.charset.{CodingErrorAction, Charset}
import java.util.Scanner
import sys.process._
import scala.collection.mutable


/**
 * Created by Aplysia_x on 2016/1/19.
 */
object CheckC {
//  val sourceBase="E:\\Coursefile\\DataStructure\\final"
  //  val destDic="E:\\Coursefile\\DataStructure\\med"
  def main(args: Array[String]) {

    if(args.length<2){
      println(s"~ sourceBase destDic")
      System.exit(1)
    }
    val sourceBase=args(0)
    val destDic=args(1)
    val files=new File(sourceBase)

    if(files.exists()){
      for (file <- files.listFiles()) {
        val fileQ=new mutable.Queue[File]
        fileQ.enqueue(file)
        val out=new PrintWriter(destDic+"\\"+file.getName+".txt")
        while(!fileQ.isEmpty) {
          val tempF=fileQ.dequeue()
          if(tempF.isDirectory){
            tempF.listFiles().foreach(fileQ.enqueue(_))
          }
          else if(tempF.getName.contains(".cpp") || tempF.getName.contains(".h")|| tempF.getName.contains(".c")) {
            val in = new FileInputStream(tempF)
            val decoder = Charset.forName("GBK").newDecoder()
            decoder.onMalformedInput(CodingErrorAction.IGNORE);
            val reader = new InputStreamReader(in, decoder)
            val scan = new Scanner(reader)
            while (scan.hasNextLine) {
              out.println(scan.nextLine())
            }
          }
          //          println(file.getName)
        }
        out.close()
      }
    }
    s"sim_c -s -R -p $destDic"!
    //      println(result)

  }
}
