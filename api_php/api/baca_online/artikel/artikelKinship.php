<?php

	require_once '../connection/conn.php';

	$artikelKinship	=	"SELECT * FROM artikel WHERE kategori = 'Kinship' ORDER BY id_artikel DESC";
	$artikelKinship	=	$conn -> query($artikelKinship);

	$response_data_kinship = null;
	while ($data = $artikelKinship -> fetch_assoc()) {
		$response_data_kinship[] = $data;
	}

	if (is_null($response_data_kinship)) {
		$status = false;
	} else {
		$status = true;
	}

	header('Content-Type: application/json');
	$response = ['status' => $status, "artikel_kinship" => $response_data_kinship];
	echo json_encode($response);
?>