<?php

	require_once '../connection/conn.php';

	$artikelGames	=	"SELECT * FROM artikel WHERE kategori = 'Games' ORDER BY id_artikel DESC";
	$artikelGames	=	$conn -> query($artikelGames);

	$response_data_games = null;
	while ($data = $artikelGames -> fetch_assoc()) {
		$response_data_games[] = $data;
	}

	if (is_null($response_data_games)) {
		$status = false;
	} else {
		$status = true;
	}

	header('Content-Type: application/json');
	$response = ['status' => $status, "artikel_games" => $response_data_games];
	echo json_encode($response);
?>