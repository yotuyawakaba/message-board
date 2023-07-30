package com.example.messageboard.app.content;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.messageboard.entity.Content;
import com.example.messageboard.service.ContentService;

import jakarta.validation.Valid;

/**
 * Messageアプリ
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ContentController {

	private final ContentService contentService;

	public ContentController(ContentService contentService) {
		this.contentService = contentService;
	}

	// メッセージの一覧を返す
	@RequestMapping("/list")
	@ResponseBody
	public List<Content> getContent(Model model) {

		List<Content> list = contentService.findAll();

		return list;
	}

	// メッセージをインサートtest
	@PostMapping("/insert")
	public void insert(@Validated @RequestBody InsertForm insertForm, BindingResult result, Model model) {

		Content insert = new Content();
		insert.setContent(insertForm.getContents());
		insert.setCreated(LocalDateTime.now());
		insert.setUpdated(LocalDateTime.now());

		contentService.insert(insert);
	}

	/**
	 * 一件メッセージを取得し、フォーム内に表示
	 * 
	 * @param insertForm
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/{id}")
	public InsertForm showUpdate(InsertForm insertForm, @PathVariable int id, Model model) {
		System.out.println(id);

		// Taskを取得(Optionalでラップ)
		Optional<Content> contentOpt = contentService.getContent(id);

		// TaskFormへの詰め直し
		Optional<InsertForm> taskFormOpt = contentOpt.map(t -> makeContentForm(t));

		// TaskFormがnullでなければ中身を取り出し
		if (taskFormOpt.isPresent()) {
			insertForm = taskFormOpt.get();
		}
		System.out.println(insertForm);

		return insertForm;
	}

	/**
	 * タスクidを取得し、一件のデータ更新
	 * 
	 * @param insertForm
	 * @param model
	 * @return
	 */
	@PutMapping("/update")
	public void update(@Valid @RequestBody InsertForm insertForm, Model model) {
		System.out.println(insertForm.getContents());
		Content content = makeContent(insertForm);
		contentService.update(content);
	}

	/**
	 * idを取得し、一件のデータ削除
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@DeleteMapping("/remove/{id}")
	public void delete(@PathVariable int id, Model model) {

		// Messageを一件削除
		contentService.deleteById(id);

	}

	/**
	 * InsertFormのデータをContentに入れて返す
	 * 
	 * @param InsertForm
	 * @return
	 */
	private Content makeContent(InsertForm insertForm) {
		Content content = new Content();
		content.setId(insertForm.getId());
		content.setContent(insertForm.getContents());

		return content;
	}

	/**
	 * TaskのデータをTaskFormに入れて返す
	 * 
	 * @param Content
	 * @return
	 */
	private InsertForm makeContentForm(Content con) {

		InsertForm insertForm = new InsertForm();

		insertForm.setContents(con.getContent());
		insertForm.setId(con.getId());

		return insertForm;
	}

}
