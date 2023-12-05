import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;

public class ResumeFrame {
    private JButton generateBtn;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField skillField;
    private JTextArea projectField;
    private JTextArea workHistory;
    private JTextField educationField;
    private JTextArea aboutArea;

    JFrame frame = new JFrame();

    public ResumeFrame(){

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,600);
        frame.setVisible(true);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
        jPanel.setBorder(new EmptyBorder(10,10,10,10));
        generateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String about = aboutArea.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String skills = skillField.getText();
                String project = projectField.getText();
                String education = educationField.getText();
                String work = workHistory.getText();

                String resumeContent = generateResume(name,about,email,phone,skills,project,education,work);
                saveResumeToPDF();

            }
        });


        jPanel.add(new Label("Name: "));
        jPanel.add(nameField);
        jPanel.add(new Label("About: "));
        jPanel.add(aboutArea);
        jPanel.add(new Label("Email: "));
        jPanel.add(emailField);
        jPanel.add(new Label("Phone No: "));
        jPanel.add(phoneField);
        jPanel.add(new Label("Skills: "));
        jPanel.add(skillField);
        jPanel.add(new Label("Projects: "));
        jPanel.add(projectField);
        jPanel.add(new Label("Education Field: "));
        jPanel.add(educationField);
        jPanel.add(new Label("Work History: "));
        jPanel.add(workHistory);
        generateBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanel.add(generateBtn);

        frame.add(jPanel,BorderLayout.CENTER);
    }
    public String generateResume(String fullName, String about, String email, String phone,String skills,String project,
                                 String education,String work) {
        return "Name: " + fullName + "\n" +
                "About: " + about + "\n" +
                "Email: " + email + "\n" +
                "Phone: " + phone + "\n" +
                "Project: " + project + "\n" +
                "Education: " + education + "\n" +
                "Work: " + work + "\n" +
                "Skills: " + skills;
    }

    public void saveResumeToPDF(){
        try(FileOutputStream fileOutputStream = new FileOutputStream(nameField.getText()+".pdf")){

            Document document = new Document();

            PdfWriter pdfWriter = PdfWriter.getInstance(document,fileOutputStream);
            document.open();

            Paragraph header = new Paragraph(nameField.getText(), FontFactory.getFont(FontFactory.COURIER_BOLD,32, Font.BOLD, BaseColor.BLACK));
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            document.add(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_BOLD,9, Font.NORMAL, BaseColor.BLACK)));
            document.add(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_BOLD,9, Font.NORMAL, BaseColor.BLACK)));
            document.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
            document.add(new Paragraph(aboutArea.getText(), FontFactory.getFont(FontFactory.COURIER_BOLD,10, Font.NORMAL, BaseColor.BLACK)));
            document.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
            document.add(new Paragraph("Contact Details", FontFactory.getFont(FontFactory.COURIER_BOLD,14, Font.NORMAL, BaseColor.BLACK)));
            document.add(new Paragraph(emailField.getText(), FontFactory.getFont(FontFactory.COURIER_BOLD,10, Font.NORMAL, BaseColor.DARK_GRAY)));
            document.add(new Paragraph(phoneField.getText(), FontFactory.getFont(FontFactory.COURIER_BOLD,10, Font.NORMAL, BaseColor.DARK_GRAY)));

            document.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
            document.add(new Paragraph("Qualification", FontFactory.getFont(FontFactory.COURIER_BOLD,14, Font.NORMAL, BaseColor.BLACK)));
            document.add(new Paragraph(educationField.getText(), FontFactory.getFont(FontFactory.COURIER_BOLD,10, Font.NORMAL, BaseColor.DARK_GRAY)));

            document.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
            document.add(new Paragraph("Skills", FontFactory.getFont(FontFactory.COURIER_BOLD,14, Font.NORMAL, BaseColor.BLACK)));
            document.add(new Paragraph(skillField.getText(), FontFactory.getFont(FontFactory.COURIER_BOLD,10, Font.NORMAL, BaseColor.DARK_GRAY)));

            document.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
            document.add(new Paragraph("Projects", FontFactory.getFont(FontFactory.COURIER_BOLD,14, Font.NORMAL, BaseColor.BLACK)));
            document.add(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_BOLD,9, Font.NORMAL, BaseColor.BLACK)));
            document.add(new Paragraph(projectField.getText(), FontFactory.getFont(FontFactory.COURIER_BOLD,10, Font.NORMAL, BaseColor.DARK_GRAY)));

            document.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
            document.add(new Paragraph("Work History", FontFactory.getFont(FontFactory.COURIER_BOLD,14, Font.NORMAL, BaseColor.BLACK)));
            document.add(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_BOLD,9, Font.NORMAL, BaseColor.BLACK)));
            document.add(new Paragraph(workHistory.getText(), FontFactory.getFont(FontFactory.COURIER_BOLD,10, Font.NORMAL, BaseColor.DARK_GRAY)));


            document.close();
            JOptionPane.showMessageDialog(frame,"Resume Created Successfully..!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
