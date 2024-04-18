<?php
//include connection file 
include "dbconnect.php";
include_once('fpdf184/fpdf.php');

class PDF extends FPDF
{
    // Page header
    function Header()
    {
        // Top color block
        $this->SetY(0);
        $this->SetX(0);
        $this->SetFillColor(250,188,2);
        $this->Cell($this->GetPageWidth(), 5, '', 0, 1, '', true);
        $this->Ln(5);

        // Title
        $this->SetFont('Arial', 'B', 24);
        $this->SetTextColor(128, 128, 128);
        $this->Cell(0, 40, "RECEIPT");

        // Move to right
        $this->SetX(-50);
        // Logo
        $this->Image('logo/company-logo.png', null, 20, 40);
        $this->Ln(50);
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
        $this->Cell(0, 10, 'Page ' . $this->PageNo() . ' of {nb}', 0, 0, 'C');
    }
}

$companyInfo = array(
    "Name" => 'Pizza Store',
    'Address' => '108 University Ave',
    'City' => 'Waterloo',
    'State' => "ON",
    'PostCode' => 'N2J 2W2',
    'Phone' => '+1(519)885-0300',
    'Email' => 'customer@pizzastore.com',
);

function drawOverview($pdf, $order) {
    global $companyInfo;
    /* company info */
    $lineHeight = 8;
    $pdf->Cell(0, $lineHeight, $companyInfo["Name"]);
    // move to right
    $pdf->SetX(-50);
    $pdf->Cell(40, $lineHeight, $order['OrderDate'], "B", 0, "C");
    $pdf->Ln($lineHeight);
    $pdf->Cell(0, $lineHeight, $companyInfo["Address"]);
    $pdf->Ln($lineHeight);
    $pdf->Cell(0, $lineHeight, 
        "{$companyInfo["City"]}, {$companyInfo["State"]}, {$companyInfo["PostCode"]}"
    );
    // move to right
    $pdf->SetX(-50);
    $pdf->Cell(40, $lineHeight, $order['OrderId'], "B", 0, "C");
    $pdf->Ln($lineHeight);
    $pdf->Cell(0, $lineHeight, $companyInfo["Phone"]);
    $pdf->Ln($lineHeight);
    $pdf->Cell(0, $lineHeight, $companyInfo["Email"]);
    $pdf->Ln(15);

    /* BILL TO and SHIP TO */
    $pdf->SetTextColor(0, 0, 80);
    $pdf->Cell(90, $lineHeight, "BILL TO", "B", 0, "L"); 
    $pdf->Cell(10);
    $pdf->Cell(90, $lineHeight, "SHIP TO", "B", 0, "L"); 
    $pdf->SetTextColor(0, 0, 0);
    $pdf->Ln($lineHeight);
    $pdf->Cell(90, $lineHeight, $order["Name"]); 
    $pdf->Cell(10);
    $pdf->Cell(90, $lineHeight, $order["Name"]); 
    $pdf->Ln($lineHeight);
    $pdf->Cell(90, $lineHeight, $order["Address"]); 
    $pdf->Cell(10);
    $pdf->Cell(90, $lineHeight, $order["ShipToAddress"]); 
    $pdf->Ln($lineHeight);
    $pdf->Cell(90, $lineHeight, $order["Phone"]); 
    $pdf->Cell(10);
    $pdf->Cell(90, $lineHeight, $order["Phone"]); 
    $pdf->Ln($lineHeight);
    $pdf->Cell(0, $lineHeight, $order["Email"]); 
    $pdf->Ln($lineHeight * 2);
}

function format_dollar($amount) {
    return number_format((float)$amount, 2, '.', ''); 
}

function format_percent($amount) {
    return number_format((float)($amount * 100), 0, '.', '')."%";
}

function drawBody($pdf, $order, $orderItems) {
    $display_heading = array(
        0 => array(
            'title' => 'DESCRIPTION', 
            'width' => 100
        ),
        1 => array(
            'title' => 'QTY', 
            'width' => 30
        ),
        2 => array(
            'title' => 'UNIT PRICE', 
            'width' => 30
        ),
        3 => array (
            'title' => 'TOTAL',
            'width' => 30
        )
    );
    $pdf->SetFont('Arial', 'B', 12);
    $pdf->SetFillColor(238,153,71);
    $pdf->SetTextColor(255, 255, 255);
    foreach ($display_heading as $heading) {
        $pdf->Cell($heading['width'], 10, $heading['title'], 0, 0, 'C', true);
    } 

    $pdf->SetTextColor(0, 0, 0);

    $subtotal = 0.0;
    foreach ($orderItems as $row) {
        $pdf->SetFont('Arial', '', 10);
        $pdf->Ln();
        $i = 0;
        foreach ($row as $column) {
            $pdf->Cell($display_heading[$i]['width'], 10, $column, 1);
            $i++;
        }
        $subtotal += $row['Total'];
    }
    $pdf->Ln(20);

    /* Prices */
    $discount = intval($order["Discount"]) / 100;
    $subtotalLessDiscount = $subtotal * (1 - $discount);
    $taxRate = 0.13;
    $totalTax = $subtotalLessDiscount * $taxRate;
    $shipping = $subtotalLessDiscount * 0.05;
    $total = $subtotalLessDiscount + $totalTax + $shipping;
    
    // move to right
    $pdf->SetX(-60);
    $pdf->Cell(20, 8, "SUBTOTAL", "", 2, "", );
    $pdf->Cell(20, 8, "DISCOUNT", "", 2, "", );
    $pdf->SetX(-90);
    $pdf->Cell(20, 8, "SUBTOTAL LESS DISCOUNT", "", 2, "", );
    $pdf->SetX(-60);
    $pdf->Cell(20, 8, "TAX RATE", "", 2, "", );
    $pdf->SetX(-62);
    $pdf->Cell(20, 8, "TOTAL TAX", "", 2, "", );
    $pdf->SetX(-78);
    $pdf->Cell(40, 8, "SHIPPING/HANDLING", "B", 2, "", );

    $pdf->SetY($pdf->GetY() - 48);
    $pdf->SetX(-40);
    $pdf->Cell(30, 8, format_dollar($subtotal), "B", 2, "R");
    $pdf->Cell(30, 8, format_percent($discount), "B", 2, "R");
    $pdf->Cell(30, 8, format_dollar($subtotalLessDiscount), "B", 2, "R");
    $pdf->Cell(30, 8, format_percent($taxRate), "B", 2, "R");
    $pdf->Cell(30, 8, format_dollar($totalTax), "B", 2, "R");
    $pdf->Cell(30, 8, format_dollar($shipping), "B", 2, "R");

    $pdf->SetFont('Arial', 'B', 14);
    $pdf->SetX(-70);
    $pdf->Cell(30, 8, "Paid", "", 0, "R");
    $pdf->Cell(30, 8, "$".format_dollar($total), "B", 2, "R");
}


function genPdf($order, $orderItems) {
    $pdf = new PDF();
    //header
    $pdf->AddPage();
    //foter page
    $pdf->AliasNbPages();
    $pdf->SetFont('Arial', '', 12);

    drawOverview($pdf, $order);
    drawBody($pdf, $order, $orderItems);

    $pdf->Output();
}

function getOrderId() {
    $url = $_SERVER['REQUEST_URI'];
    $parts = parse_url($url);
    parse_str($parts['query'], $query);

    return $query['orderId'];
}

$orderId = getOrderId();
$orderInfo = mysqli_query($conn, 
    "SELECT o.orderDate as OrderDate, o.ordersId as OrderId, 
        c.Name, c.Address, c.City, c.Contact as Phone, c.EmailId as Email,
        d.Address as ShipToAddress,
        cp.Discount
    FROM Orders o
    LEFT JOIN Customers c on c.customerId = o.customerId
    LEFT JOIN Delivery d on d.ordersId = o.ordersId
    LEFT JOIN Coupons cp on cp.idCoupons = o.idCoupons
    WHERE o.ordersId={$orderId};"
    )
    or die("database error:" . mysqli_error($conn));

$orderItems = mysqli_query($conn, 
    "SELECT CONCAT(p.Name, '-', p.Size, '-', p.Crust, '-', p.Toppings) as Name, 
    op.quantity as Quantity, p.Price, (op.quantity * p.Price) as Total
    FROM Orders_has_Pizzas op
    INNER JOIN Pizzas p on p.pizzaId = op.pizzaId
    WHERE op.ordersId = {$orderId};"
  ) or 
    die("database error:" . mysqli_error($conn));

genPdf($orderInfo->fetch_array(), $orderItems);

?>
