package com.teamone.approvalserver.Services.Email;

import com.teamone.approvalserver.Models.ChainModel;
import com.teamone.approvalserver.Models.DocumentModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PdfFactory {

    public void GenerateAudit(DocumentModel dm) throws IOException, InterruptedException {
        FileWriter fileWriter = new FileWriter("src/main/resources/audit.tex");
        PrintWriter printWriter = new PrintWriter("src/main/resources/audit.tex");

        //write TeX file for the Audit
        printWriter.print("\\documentclass{article}\n" +
                "\\usepackage[a4paper, total={6in, 10in}]{geometry}\n");

        printWriter.printf("\\title{%s-%s-%s}\n", dm.getCustomer(), dm.getProject(), dm.getName());
        printWriter.printf("\\author{Document Originator: %s}\n", dm.getOriginator());
        printWriter.print("\\date{}\n");

        printWriter.print("\\begin{document}\n" +
                "\\setlength\\parindent{0pt}\n");

        printWriter.print("\\maketitle\n");

        printWriter.print("\\section*{Description}");
        printWriter.printf("%s}\n", dm.getDescription());

        printWriter.print("\\section*{Review Audit}");
        printWriter.print("\\begin{table}[h!]\n" +
                "\\centering\n" +
                "\\begin{tabular}{llll}\n" +
                "\\textbf{Position} & \\textbf{Reviewer}     & \\textbf{Timestamp} & \\textbf{Status} \\\\");

        for(ChainModel cm : dm.getChainList()) {
            printWriter.print("   &     &    &   \\ \n");
            printWriter.printf("%s & %s & %s & %s \\ \n ", cm.getPosition(), cm.getUserId(), cm.getTimeStamp(), cm.getApproved());
        }

        printWriter.print("\\end{document}\n");
        printWriter.close();




        ProcessBuilder pb = new ProcessBuilder("pdflatex", "audit.tex")
                .inheritIO()
                .directory(new File("src/main/resources"));
        Process process = pb.start();

        process.waitFor();
    }
}
