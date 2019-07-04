# Pdfparser_Project
Pdfparser_Project deals with parsing invoice pdf received in e-mail and extract some values. Some critical values:
1.Invoice number
2.Invoice date
3.Sold to
4.Ship to
5.Remit to
6.Customer.P.O.
7.Total invoice.

The next step is to store the above values in database by connecting db.Finally the user approves the invoice he want and the acknowledgement mail is sent back to the person who has sent the invoice.

IDE USED: ECLIPSE
DB:       Oracle SQLDeveloper
INVOICE PDF USED HERE IS ATTACHED.(Acushnet.pdf)

Pre-requisites:
Include the following jar files in your project.
pdfbox-app-2.0.16.jar
ojdbc6.jar
fontbox-2.0.16.jar
mail.jar

AccountsPayable.java: This is tha main class where it calls other classes.Here change userName(emailid) and password accordingly.In dbConnection() mention appropriate url,user,password.

ReceiveEmailWithAttachment.java: It receives email from the mentioned user and saves it in the mentioned folder.

ReadPdf.java: It reads the whole pdf by line by line and extracts the required details. These details are stored in db.

UpdateStatus.java: Displays the invoice numbers from db and asks the user to approve any number.

Sendemail.java: Sends the email as approved to the user.



