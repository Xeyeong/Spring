package board;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO implements BoardService {
	@Autowired @Qualifier("hanul") private SqlSession sql;
	
	@Override
	public int board_insert(BoardVO vo) {
		//방명록 글 저장 후
		int insert = sql.insert("board.insert", vo);
		
		//방명록 글에 첨부된 파일을 저장
		if( insert==1 && vo.getFileList()!=null ) {
			sql.insert("board.file_insert", vo);
		}
		return insert;
	}

	@Override
	public BoardPageVO board_list(BoardPageVO page) {
		//글의 총건수를 조회
		page.setTotalList( sql.selectOne("board.total", page) );
		//공지글 10건 조회
		page.setList( sql.selectList("board.list", page) );
		return page;
	}

	@Override
	public BoardVO board_info(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int board_update(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int board_read(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int board_delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
