<?php
//include connection file 
include "Peng_Liu_A3_dbconnect";
include_once('fpdf184/fpdf.php');

class PDF extends FPDF
{
    // Page header
    function Header()
    {
        // Logo
        $this->Image('Peng_Liu_A3_conestogalogo.png', 10, 10, 20);
        $this->Ln(10);
        $this->SetFont('Arial', 'B', 13);
    }

    // Page footer
    function Footer()
    {
        // Position at 1.5 cm from bottom
        $this->SetY(-15);
        // Arial italic 8
        $this->SetFont('Arial', 'I', 8);
        // Page number
        $this->Cell(0, 10, 'Page ' . $this->PageNo() . '/{nb}', 0, 0, 'C');
    }
}

$display_heading = array('user_id' => 'ID', 'name' => 'Name', 'email' => 'Email', 'department' => 'Department', 'role' => 'Role');

$result = mysqli_query($conn, "SELECT user_id, name, email, department, role FROM employees") or die("database error:" . mysqli_error($conn));
$header = mysqli_query($conn, "SHOW columns FROM employees WHERE field != 'created_on'");

$pdf = new PDF();
//header
$pdf->AddPage();
//foter page
$pdf->AliasNbPages();
$pdf->SetFont('Arial', 'B', 16);
foreach ($header as $heading) {
    $pdf->Cell(35, 10, $display_heading[$heading['Field']], 1);
}
foreach ($result as $row) {
    $pdf->SetFont('Arial', '', 10);
    $pdf->Ln();
    foreach ($row as $column)
        $pdf->Cell(35, 10, $column, 1);
}
$pdf->Output();
?>
