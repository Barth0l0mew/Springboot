package org.itstep;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.itstep.customer.Customer;
import org.itstep.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    @Autowired
    private CustomerService customerService;
    public String exportReport(List<Customer> customers, String template, String report, String reportFormat) throws FileNotFoundException, JRException {
        File file = ResourceUtils.getFile("C:\\Users\\gk\\Springboot\\JasperReports\\src\\main\\resources\\templates\\"+template);
        //File file = ResourceUtils.getFile("classpath:templates/"+template);
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(customers);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy","Alex");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        if (reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,"C:\\Users\\gk\\Springboot\\JasperReports\\src\\main\\resources\\reports\\"+report);
           // JasperExportManager.exportReportToHtmlFile(jasperPrint, "classpath:reports/"+report);
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH🇲🇲ss");
        Calendar cal = Calendar.getInstance();
        String sdate = dateFormat.format(cal.getTime());
        return "report has been generated: "+sdate;
    }

}
