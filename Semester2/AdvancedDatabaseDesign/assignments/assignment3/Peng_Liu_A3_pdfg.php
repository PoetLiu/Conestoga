<?php
//include connection file 
include "dbconnect.php";
include_once('fpdf184/fpdf.php');

class PDF extends FPDF
{
    // Page header
    function Header()
    {
        // Logo
        $this->Image('logo/conestogalogo.png', ($this->GetPageWidth()-20)/2, 10, 20);
        $this->Ln(20);
        $this->SetFont('Arial', 'B', 13);
    }

    // Page footer
    function Footer()
    {
        // Position at 1.5 cm from bottom
        $this->SetY(-15);
        // Arial italic 8
        $this->SetFont('Arial', 'B', 8);
        // Page number
        $this->Cell(0, 10, 'Page ' . $this->PageNo() . 'of {nb}', 0, 0, 'C');
    }
}

function getCurrentDate() {
    date_default_timezone_set("Canada/Eastern");
    return date("M d, Y");
}

function genPdf($student, $records) {
    $pdf = new PDF();
    //header
    $pdf->AddPage();
    //foter page
    $pdf->AliasNbPages();
    $pdf->SetFont('Arial', '', 14);

    // table
    $name = "Name of the student: {$student['first_name']} {$student['last_name']}";
    $pdf->Cell($pdf->GetStringWidth($name), 10, $name, 0);
    // moves to right
    $pdf->Cell(0, 10, "Date: " . getCurrentDate(), 0, 0, "R");
    $pdf->Ln();
    $pdf->Cell(0, 10, "Student ID: {$student['student_id']}"); 
    $pdf->Ln(20);

    // filed_in_db => display_heading
    $display_heading = array(
        'course_title' => 'Course', 'grade' => 'Grade', 'term_name' => 'Term'
    );

    $pdf->SetFont('Arial', 'B', 14);
    $pdf->SetFillColor(217,225,243);
    foreach ($display_heading as $heading) {
        $pdf->Cell(60, 10, $heading, 1, 0, '', true);
    }
    foreach ($records as $row) {
        $pdf->SetFont('Arial', '', 10);
        $pdf->Ln();
        foreach ($row as $column)
            $pdf->Cell(60, 10, $column, 1);
    }
    $pdf->Ln(30);

    // signature
    $pdf->SetFont('Arial', '', 10);
    // moves to right
    $pdf->Cell(120);
    $pdf->Cell(50, 6, "Signature", "T");

    $pdf->Output("F", "Peng_Liu_A3_transcript_{$student['first_name']}.pdf");
}

$students = mysqli_query($conn, 
    "SELECT student_id, first_name, last_name FROM student ORDER BY student_id"
    ) or 
    die("database error:" . mysqli_error($conn));
    
foreach ( $students as $student ) {
    $results = mysqli_query($conn, 
    "SELECT c.course_title, e.grade, t.term_name
        FROM
            enrollment e
        INNER JOIN
            course c on c.course_id = e.course_id 
        INNER JOIN 
        	term t on t.term_name = e.term_name 
        WHERE e.student_id = {$student["student_id"]} and e.grade IS NOT NULL
    ") or 
    die("database error:" . mysqli_error($conn));
    genPdf($student, $results);
}
?>
