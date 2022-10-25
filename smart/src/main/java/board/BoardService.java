package board;

public interface BoardService {
	//CRUD
	int board_insert(BoardVO vo);	//방명록 글 신규저장
	BoardPageVO board_list(BoardPageVO page); //방명록 글 목록조회(페이징처리)
	BoardVO board_info(int id); 	//방명록 정보 조회
	int board_update(BoardVO vo); 	//방명록 글변경저장
	int board_read(int id); 		//방명록 글조회수 증가처리
	int board_delete(int id); 		//방명록 글삭제
}
