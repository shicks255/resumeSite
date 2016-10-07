package com.steven.hicks;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Created by Steven on 10/6/2016.
 */

public final class Utils
{
    public static String PATH_TO_META_INF;
    public static String PATH_TO_WEB_ROOT;
    public static String PATH_TO_CLASSES;

    public static ServletContext m_servletContext;

    public static Date getTodaysDate()
    {
        Calendar test = Calendar.getInstance();
        test.set(Calendar.MILLISECOND,0);
        test.set(Calendar.HOUR_OF_DAY,0);
        test.set(Calendar.MINUTE,0);
        test.set(Calendar.SECOND,0);

        return test.getTime();
    }

    public static String getString(Object object)
    {
        if (object == null) return "";
        return Objects.toString(object, "").trim();
    }

    public static String formatNumber(Number number)
    {
        if (number == null) return "";

        try
        {
            NumberFormat numberFormat = NumberFormat.getInstance();
            return numberFormat.format(number);
        }
        catch (Exception e)
        {
            return "";
        }
    }

    public static Date convertStringToDate(String date)
    {

        if (date == null) return null;
        date = date.trim();

        int lastSlash = date.lastIndexOf("/");
        boolean oneDigitYear = date.length() - lastSlash == 2;
        boolean twoDigitYear = date.length() - lastSlash == 3;
        boolean fourDigitYear = date.length() - lastSlash == 5;

        if (lastSlash > 0)
        {
            if (oneDigitYear)
            {
                date = date.substring(0, lastSlash) + "/0" + date.substring(date.length() - 1);
                return convertStringToDate(date, "MM/dd/yy");
            }
            if (twoDigitYear) return convertStringToDate(date, "MM/dd/yy");
            if (fourDigitYear) return convertStringToDate(date, "MM/dd/yyyy");
        }
        return null;
    }

    public static Date convertStringToDate(String date, String format)
    {
        if (date == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        try
        {
            return sdf.parse(date);
        }
        catch (ParseException e)
        {

        }
        return null;
    }

    public static Date convertStringToDateTime(String date)
    {
        if (date==null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy h:mma");
        try
        {
            return sdf.parse(date);
        }
        catch (ParseException e)
        {
            // Don't do anything.  We really don't care why it failed.
        }
        return null;
    }

    public static Date convertStringToDateTimeWithSeconds(String date)
    {
        if (date==null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        try
        {
            return sdf.parse(date);
        }
        catch (ParseException e)
        {
            // Don't do anything.  We really don't care why it failed.
        }
        return null;
    }

    public static String convertDateToString(Date theDate)
    {
        if (theDate == null) return "";
        return new SimpleDateFormat("MM/dd/yyyy").format(theDate);
    }

    public static String convertDateToString2(Date theDate)
    {
        if (theDate==null) return "";

        // If it might be a two year date, lets user a different formatter
        return new SimpleDateFormat("M/d/yyyy").format(theDate);
    }

    public static String convertDateTimeToString(Date theDate)
    {
        if (theDate==null) return "";
        return new SimpleDateFormat("M/d/yyyy h:mma").format(theDate);

    }
    public static String convertDateTimeToTimeString(Date theDate)
    {
        if (theDate==null) return "";
        return new SimpleDateFormat("h:mma").format(theDate);
    }

    public static String convertDateTimeToString2(Date theDate)
    {
        if (theDate==null) return "";
        return new SimpleDateFormat("M/d/yyyy h:mm.ss a").format(theDate);
    }

    public static long convertToLong(String number)
    {
        if (number==null || number.trim().length()==0) return 0;
        number = number.trim();

        long value=0;
        try
        {
            value = Long.parseLong(number);
        }
        catch (NumberFormatException e)
        {
        }

        return value;
    }

    public static Long convertToLong2(String number)
    {
        if (number==null || number.trim().length()==0) return null;
        number = number.trim();

        long value=0;
        try
        {
            value = Long.parseLong(number);
        }
        catch (NumberFormatException e)
        {
        }

        return value;
    }

    public static int convertToInteger(String number)
    {
        if (number==null || number.trim().length()==0) return 0;
        number = number.trim();

        int value=0;
        try
        {
            value = Integer.parseInt(number);
        }
        catch (NumberFormatException e)
        {
        }

        return value;
    }
    public static Integer convertToInteger2(String number)
    {
        if (number==null || number.trim().length()==0) return null;
        number = number.trim();

        int value=0;
        try
        {
            value = Integer.parseInt(number);
        }
        catch (NumberFormatException e)
        {
            return null;
        }

        return value;
    }

//    public static BigDecimal convertToBigDecimal(String value)
//    {
//        if (value==null || value.trim().length()==0) return null;
//        value = value.trim().replaceAll("\\,", "");
//
//       comment-- DO NOT BRING THAT BACK. It causes problems especially in the SGOHandler.  Leaving this as a comment in case I think its a good idea in the future.
//        comment--value = convertToBasicNumeric(value);
//        try
//        {
//            return new BigDecimal(value);
//        }
//        catch(NumberFormatException e)
//        {
//
//        }
//
//        return null;
//    }

    public static File createTemporaryFile(String extension)
    {
        if (extension!=null && extension.charAt(0)=='.' && extension.length()>1)
            extension = extension.substring(1);

        File tempFile = null;
        try
        {
            tempFile = File.createTempFile("temp", "." + extension, new File(System.getProperty("java.io.tmpdir")));
        }
        catch (IOException e)
        {
            System.out.println(Utils.getString(e.getMessage()));
        }

        return tempFile;
    }

    public static String getExtension(String filename)
    {
        String extension = "";
        if (filename!=null && filename.length()>3 && filename.indexOf('.')>=0)
            extension = filename.substring(filename.lastIndexOf('.') + 1);

        return extension;
    }

    public static String convertToSimpleString(String text)
    {
        if (text==null) return "";
        text = text.trim();
        StringBuilder newCodeBuilder = new StringBuilder(text.length());
        for(char c : text.toCharArray())
        {
            boolean safeToAdd = false;
            if (c >= '0' && c<= '9' ) safeToAdd = true;
            if (c >= 'A' && c<= 'Z' ) safeToAdd = true;
            if (c >= 'a' && c<= 'z' ) safeToAdd = true;
            if (c == '_'  || c=='-' || c=='.' || c=='/' || c==':' || c==';' || c==' ') safeToAdd = true;

            if (safeToAdd)
                newCodeBuilder.append(c);
        }

        return newCodeBuilder.toString();
    }

    public static String getMimeType(String filename)
    {
        if (filename == null || filename.length()<4 ) return "";
        filename = filename.toLowerCase();
        String mimetype = "";

        if (filename.indexOf('.')>=0)
        {
            String extension = filename.substring(filename.toLowerCase().lastIndexOf('.') + 1);

            if (extension.equals("ics"))        mimetype = "text/calendar";
            if (extension.equals("ppt"))        mimetype = "application/ms-powerpoint";
            if (extension.equals("pptx"))       mimetype = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
            if (extension.equals("doc"))        mimetype = "application/msword";
            if (extension.equals("rtf"))        mimetype = "application/msword";
            if (extension.equals("docx"))       mimetype = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            if (extension.equals("dotx"))       mimetype = "application/vnd.openxmlformats-officedocument.wordprocessingml.template";
            if (extension.equals("xls"))        mimetype = "application/vnd.ms-excel";
            if (extension.equals("xlsx"))       mimetype = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            if (extension.equals("xltx"))       mimetype = "application/vnd.openxmlformats-officedocument.spreadsheetml.template";
            if (extension.equals("pdf"))        mimetype = "application/pdf";
            if (extension.equals("jpg"))        mimetype = "image/jpeg";
            if (extension.equals("gif"))        mimetype = "image/gif";
            if (extension.equals("txt"))        mimetype = "text/plain";
            if (extension.equals("log"))        mimetype = "text/plain";
            if (extension.equals("bz2"))        mimetype = "application/bzip2";
            if (extension.equals("gz"))         mimetype = "application/gzip";
            if (extension.equals("csv"))        mimetype = "text/csv";
            if (extension.equals("flipchart"))  mimetype = "application/Inspire flipchart";
            if (extension.equals("png"))        mimetype = "image/png";
            if (extension.equals("crx"))        mimetype = "application/unknown";
            if (extension.equals("ttf"))        mimetype = "application/octet-stream";
            if (extension.equals("otf"))        mimetype = "application/octet-stream";
        }
        return mimetype;
    }

    public static void writeFileToResponse(HttpServletResponse response, byte[]binary, String filename) throws IOException
    {
        response.setHeader("Content-Length", "" + (binary.length) );
        response.setContentType(getMimeType(filename));
        response.addHeader("Content-Disposition", "attachment; filename=" + filename);

        // Write the file back to the client
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(binary);
    }

    public static void writeFileToResponse(HttpServletRequest request, HttpServletResponse response, File file, String filename, boolean deleteFile) throws IOException
    {
        if (!file.exists() && !file.isFile()) return;

        response.setContentLengthLong(file.length());
        response.setContentType(getMimeType(filename));
        response.addHeader("Content-Disposition", "attachment; filename=" + filename);

        // Write the file back to the client
        byte[] buffer = new byte[32_000];
        ServletOutputStream outputStream = response.getOutputStream();
        try(FileInputStream inputStream = new FileInputStream(file))
        {
            for( int bytesRead = inputStream.read(buffer); bytesRead>=0; bytesRead = inputStream.read(buffer) )
                outputStream.write(buffer,0,bytesRead);
            outputStream.flush();
        }

        if (deleteFile)
        {
//            if (UserAgentAnalyzer.getBrowserName(request.getHeader("user-agent")).startsWith("Edge"))
//                deleteFileAfterDelay(file, 10_000);
//            else
                file.delete();
        }
    }

    public static void writeFileToResponseInline(HttpServletRequest request, HttpServletResponse response, File file, String filename, boolean deleteFile) throws IOException
    {
        if (!file.exists() && !file.isFile()) return;

        response.setContentLengthLong(file.length());
        response.setContentType(getMimeType(filename));
        response.setHeader("Content-Disposition", "filename=\"" + filename + "\"");

        // Write the file back to the client
        byte[] buffer = new byte[32_000];
        ServletOutputStream outputStream = response.getOutputStream();

        try(FileInputStream inputStream = new FileInputStream(file))
        {
            for( int bytesRead = inputStream.read(buffer); bytesRead>=0; bytesRead = inputStream.read(buffer) )
                outputStream.write(buffer,0,bytesRead);
            outputStream.flush();
        }

        if (deleteFile)
        {
//            if (UserAgentAnalyzer.getBrowserName(request.getHeader("user-agent")).startsWith("Edge"))
//                deleteFileAfterDelay(file, 10_000);
//            else
                file.delete();
        }
    }

    public static void writeFileToResponseInline(HttpServletResponse response, byte[] content, String mimeType, String filename) throws IOException
    {
        response.setContentLengthLong(content.length);
        response.setHeader("Content-Disposition", "filename=\"" + filename + "\"");
        response.setContentType(mimeType);

        // Write the file back to the client
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(content);
    }

    public static void writeFileToResponseInline(HttpServletResponse response, byte[] content, String mimeType) throws IOException
    {
        response.setContentLengthLong(content.length);
        response.setContentType(mimeType);

        // Write the file back to the client
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(content);
    }

//    public static void deleteFileAfterDelay(File fileToDelete, int millisecondDelay) throws InterruptedException
//    {
//        Thread delayedThread = new Thread(
//                () ->
//                {
//                    sleep(millisecondDelay);
//                    if (fileToDelete.exists() && fileToDelete.isFile())
//                        fileToDelete.delete();
//                });
//        sleep(100);
//        delayedThread.start();
//    }

    public static byte[] getBinaryFromFile(String filename) throws IOException
    {
        return Files.readAllBytes(Paths.get(filename));
    }

    public static byte[] getBinaryFromFile(File file) throws IOException
    {
        return Files.readAllBytes(file.toPath());
    }

    public static File writeBinaryToTempFile(byte[] binary, String extension)
    {
        File tempFile = Utils.createTemporaryFile(extension);
        try(FileOutputStream outputStream = new FileOutputStream(tempFile))
        {
            outputStream.write(binary);
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }

        return tempFile;
    }

    public static boolean isDateInRange(Date testDate, Date startDate, Date endDate)
    {
        if (testDate == null) return false;

        if (startDate != null && endDate != null)
        {
            if (startDate.before(testDate) || startDate.equals(testDate))
                if (endDate.after(testDate) || endDate.equals(testDate))
                    return true;
        }

        if (startDate!=null && endDate==null)
            return startDate.before(testDate) || startDate.equals(testDate);

        if (endDate!=null && startDate == null)
            return endDate.after(testDate) || endDate.equals(testDate);

        return false;
    }

    public static Date addDays(Date sourceDate, int days)
    {
        if (sourceDate == null) return null;
        if (days==0) return sourceDate;

        Calendar temp = Calendar.getInstance();
        temp.setTime(sourceDate);
        temp.add(Calendar.DAY_OF_MONTH, days);
        return temp.getTime();
    }

    public static Date addHours(Date sourceDate, int hours)
    {
        if (sourceDate == null) return null;
        if (hours==0) return sourceDate;

        Calendar temp = Calendar.getInstance();
        temp.setTime(sourceDate);
        temp.add(Calendar.HOUR, hours);
        return temp.getTime();
    }

    public static BigDecimal roundToWholeNumber(BigDecimal number)
    {
        try
        {
            if(number.compareTo(new BigDecimal(0).setScale(2))==0)
            {
                number = number.setScale(1);
            }
            return number.round(new MathContext( number.precision() - number.scale()    , RoundingMode.HALF_UP));
        }
        catch(ArithmeticException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static BigDecimal roundToNearestQuarter(BigDecimal number)
    {

        try
        {
            number = number.divide(new BigDecimal(".25"),0,BigDecimal.ROUND_HALF_UP);
            number = number.round(new MathContext(1)).multiply(new BigDecimal(".25"));

            if(number.compareTo(new BigDecimal("0.00"))==0)
                number = new BigDecimal(".25");

            return number;
        }
        catch(ArithmeticException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String getMonthCode(Date date)
    {
        if (date==null) return "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int month = calendar.get(Calendar.MONTH) + 1;  // Month is zero based for some reason
        switch(month)
        {
            case 1: return "JANUARY";
            case 2: return "FEBRUARY";
            case 3: return "MARCH";
            case 4: return "APRIL";
            case 5: return "MAY";
            case 6: return "JUNE";
            case 7: return "JULY";
            case 8: return "AUGUST";
            case 9: return "SEPTEMBER";
            case 10: return "OCTOBER";
            case 11: return "NOVEMBER";
            case 12: return "DECEMBER";
        }
        return "";
    }

    public static Calendar getCalendarFromDate(Date date)
    {
        if (date == null) return null;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    public static int getYearsBetweenDateAndToday(Date date1)
    {
        if (date1==null ) throw new IllegalArgumentException("Dates cannot be null!");
        Date date2 = Utils.getTodaysDate();

        return getYearsBetweenDates(date1, date2);
    }

    public static int getYearsBetweenDates(Date date1, Date date2)
    {
        if (date1==null || date2==null) throw new IllegalArgumentException("Dates cannot be null!");

        if (date1.equals(date2)) return 0;

        LocalDate lDate1 = LocalDateTime.ofInstant(date1.toInstant(), ZoneId.systemDefault()).toLocalDate();
        LocalDate lDate2 = LocalDateTime.ofInstant(date2.toInstant(), ZoneId.systemDefault()).toLocalDate();

        return Period.between(lDate1, lDate2).getYears();
    }

    public static int getNumberOfDays(Date date1, Date date2)
    {
        if (date1==null || date2==null) throw new IllegalArgumentException("Dates cannot be null!");

        if (date1.equals(date2)) return 1;

        LocalDate lDate1 = LocalDateTime.ofInstant(date1.toInstant(), ZoneId.systemDefault()).toLocalDate();
        LocalDate lDate2 = LocalDateTime.ofInstant(date2.toInstant(), ZoneId.systemDefault()).toLocalDate();

        return (int) ChronoUnit.DAYS.between(lDate1, lDate2);
    }

    public static int getHoursBetweenDates(Date date1, Date date2)
    {
        if (date1==null || date2==null) throw new IllegalArgumentException("Dates cannot be null!");

        if (date1.equals(date2)) return 0;

        LocalDateTime lDate1 = LocalDateTime.ofInstant(date1.toInstant(), ZoneId.systemDefault());
        LocalDateTime lDate2 = LocalDateTime.ofInstant(date2.toInstant(), ZoneId.systemDefault());

        return (int) ChronoUnit.HOURS.between(lDate1, lDate2);
    }

    public static int getMinutesBetweenDates(Date date1, Date date2)
    {
        if (date1==null || date2==null) throw new IllegalArgumentException("Dates cannot be null!");

        if (date1.equals(date2)) return 0;

        LocalDateTime lDate1 = LocalDateTime.ofInstant(date1.toInstant(), ZoneId.systemDefault());
        LocalDateTime lDate2 = LocalDateTime.ofInstant(date2.toInstant(), ZoneId.systemDefault());

        return (int) ChronoUnit.MINUTES.between(lDate1, lDate2);
    }

    public static String encodeXML(String value)
    {
        if (value==null) return "";
        value = value.replaceAll("\"", "&quot;");
        value = value.replaceAll("&", "&amp;");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("<", "&lt;");
        value = value.replaceAll(">", "&gt;");
        return value;
    }

    public static String decodeXML(String value)
    {
        if (value == null) return "";
        value = value.replaceAll("&quot;", "\"");
        value = value.replaceAll("&amp;", "&");
        value = value.replaceAll("&apos;", "'");
        value = value.replaceAll("&#39;", "'");
        value = value.replaceAll("&lt;", "<");
        value = value.replaceAll("&gt;", ">");
        value = value.replaceAll("&ndash;", "-");
        value = value.replaceAll("&mdash;", "-");
        return value;
    }

    public static String convertFromCamelCaseToEnglish(String propertyName)
    {
        // Convert to some nice display text; for example: make schoolYear School Year
        StringBuilder answer = new StringBuilder(propertyName.length() * 2);

        int size = propertyName.length();
        boolean lastCharWasCapitol = false;

        for (int n=0; n<size; n++)
        {
            char c = propertyName.charAt(n);

            // If we are lower case; just add it to the output string
            if (c>='A' && c<='Z')
            {
                if (lastCharWasCapitol==false)
                    answer.append(' ');
                answer.append(c);
                lastCharWasCapitol = true;
            }
            else
            {
                lastCharWasCapitol = false;
                if (n>0)
                    answer.append(c);
                else
                    answer.append( ("" + c).toUpperCase());
            }
        }

        return answer.toString();
    }

    public static boolean contains(List<Object> objects, Object object)
    {
        return !(objects == null || objects.size() == 0) && objects.contains(object);
    }

    public static String getPathToMetaInf()
    {
        return PATH_TO_META_INF;
    }

    public static String getPathToClasses()
    {
        return PATH_TO_CLASSES;
    }

    public static String getPathToWebroot()
    {
        return PATH_TO_WEB_ROOT;
    }

    public static Date combineDateAndTime(Date date, Date time)
    {
        if (date==null || time==null) return null;
        Calendar combined = Calendar.getInstance();
        combined.setTime(date);


        Calendar t = Calendar.getInstance();
        t.setTime(time);

        combined.set(Calendar.HOUR_OF_DAY, t.get(Calendar.HOUR_OF_DAY));
        combined.set(Calendar.MINUTE, t.get(Calendar.MINUTE));
        combined.set(Calendar.SECOND, t.get(Calendar.SECOND));
        combined.set(Calendar.MILLISECOND, t.get(Calendar.MILLISECOND));


        return combined.getTime();
    }

    public static byte[] readAllBytes(InputStream inputStream) throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[16384];
        int bytesRead = inputStream.read(buffer);

        while(bytesRead > 0)
        {
            bos.write(buffer,0,bytesRead);
            bytesRead = inputStream.read(buffer);
        }

        bos.close();
        return bos.toByteArray();
    }

    public static List<String> breakIntoWords(String text)
    {
        List<String> _words = new ArrayList<>(50);

        // Break it into lines first
        String[] lines = text.split("\r\n");

        // For each line into words
        for(int lineIndex = 0; lineIndex<lines.length; lineIndex++)
        {
            String[] words = lines[lineIndex].split(" ");
            for(String word : words)
                if (word.trim().length()>0) _words.add(word);
            if (lineIndex<lines.length-1)
                _words.add("\r\n");
        }

        return _words;
    }

    public static String combineWords(List<String> words)
    {
        StringBuilder b = new StringBuilder();
        for(String word : words)
            b.append(word).append(' ');

        return b.toString().trim();
    }

    public static String fixInvalidCharactersFromWord(String stringToFix)
    {
        if (stringToFix == null) return "";

        String fixed = stringToFix.replace('\u0093','\"');
        fixed = fixed.replace('\u0094','\"');
        fixed = fixed.replace('\u0091','\'');
        fixed = fixed.replace('\u0092','\'');
        fixed = fixed.replace('\u0095','*');
        fixed = fixed.replace('\u0085',':');
        fixed = fixed.replace('\u0097','-');
        fixed = fixed.replace('\u0096','-');
        fixed = fixed.replaceAll("&#1048781;","?");
        fixed = fixed.replaceAll("&#8800;","!=");
        fixed = fixed.replace('\t',' ');
        return fixed;
    }

    public static int convertToPercentage(int current, int total)
    {
        if (current == 0 || total == 0 ) return 0;

        return (current * 100) / total;
    }

    public static String getOperatingSystemName()
    {
        String operatingSystem = System.getProperty("os.name") + " " + System.getProperty("os.version") + " " + System.getProperty("os.arch");

        if (operatingSystem.contains("Linux") || operatingSystem.contains("linux"))
        {
            try
            {
                File redhatIssue = new File("/etc/redhat-release");
                if (redhatIssue.exists() && redhatIssue.isFile())
                {
                    List<String> lines = Files.readAllLines(Paths.get("/etc/redhat-release"));
                    if (lines.size()>0) return lines.get(0).trim();
                }

                List<String> lines = Files.readAllLines(Paths.get("/etc/issue"));
                for(String line : lines)
                {
                    if (lines.size()>0)
                    {
                        operatingSystem = line;

                        // These lines are mostly to clean up a SuSE /etc/issue which looks like this:
                        // Welcome to SUSE Linux Enterprise Server 11 SP1  (x86_64) - Kernel \r (\l).
                        if (operatingSystem.startsWith("Welcome to "))
                            operatingSystem = operatingSystem.substring(11);

                        int kernelIndex = operatingSystem.indexOf(" - Kernel");
                        if (kernelIndex>0)
                            operatingSystem = operatingSystem.substring(0, kernelIndex);
                        break;
                    }
                }
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }

        return operatingSystem.trim();
    }

    public static ServletContext getServletContext()
    {
        return m_servletContext;
    }

    public static void setServletContext(ServletContext servletContext)
    {
        m_servletContext = servletContext;
    }

    public static Date getDaysFromDate(Date startingDate, int offset)
    {
        if (startingDate==null || offset==0) return startingDate;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startingDate);
        calendar.add(Calendar.DAY_OF_MONTH, offset);
        return calendar.getTime();
    }

    public static String toLowerCase(String text)
    {
        if (text==null || text.isEmpty()) return "";
        return text.toLowerCase();
    }

    public static Date getDate(int month, int day, int year)
    {
        return Utils.convertStringToDate("" + month + "/" + day + "/" + year);
    }

    public static int compareDates(Date date1, Date date2)
    {
        if (date1==null && date2==null) return 0;
        if (date1==null) return -1;
        if (date2==null) return 1;

        return date1.compareTo(date2);
    }

    public static int compareBigDecimals(BigDecimal bigDecimal1, BigDecimal bigDecimal2)
    {
        if (bigDecimal1==null && bigDecimal2==null) return 0;
        if (bigDecimal1==null) return -1;
        if (bigDecimal2==null) return 1;

        return bigDecimal1.compareTo(bigDecimal2);
    }

    public static String getStartOfString(String text, int maxCharacters)
    {
        if (text.length()<maxCharacters || text.isEmpty()) return text;

        return text.substring(0, maxCharacters) + "...";
    }

    public static int getCurrentYear()
    {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.YEAR);
    }

    public static int getYearFromDate(Date asOfDate)
    {
        if (asOfDate==null) return 0;

        Calendar c = Calendar.getInstance();
        c.setTime(asOfDate);
        return c.get(Calendar.YEAR);
    }

    public static String convertToPhoneNumber(String phoneNumber)
    {
        if (phoneNumber==null) return "";
        phoneNumber = phoneNumber.trim();
        if (phoneNumber.length()!=10) return phoneNumber;
        return phoneNumber.substring(0,3) + "-" + phoneNumber.substring(3,6) + "-" + phoneNumber.substring(6);
    }

    public static File writeLinesToFile(List<String> lines)
    {
        File logFile = Utils.createTemporaryFile(".txt");
        try
        {
            PrintWriter writer = new PrintWriter(logFile, "ISO-8859-1");
            for (String line : lines)
                writer.write(line + "\r\n");
            writer.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        return logFile;
    }

    public static String getBigDecimalAsString(BigDecimal value)
    {
        if (value==null) return "";
        return value.toString();
    }

    public static String getTempDirectory()
    {
        return System.getProperty("java.io.tmpdir");
    }
}
