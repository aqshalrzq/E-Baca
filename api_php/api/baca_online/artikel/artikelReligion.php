<?php

	require_once '../connection/conn.php';

	$artikelReligion	=	"SELECT * FROM artikel WHERE kategori = 'Religion' ORDER BY id_artikel DESC";
	$artikelReligion	=	$conn -> query($artikelReligion);

	$response_data_religion = null;
	while ($data = $artikelReligion -> fetch_assoc()) {
		$response_data_religion[] = $data;
	}

	if (is_null($response_data_religion)) {
		$status = false;
	} else {
		$status = true;
	}

	header('Content-Type: application/json');
	$response = ['status' => $status, "artikel_religion" => $response_data_religion];
	echo json_encode($response);
?>