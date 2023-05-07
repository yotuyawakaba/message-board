package com.example.messageboard.app.content;

import jakarta.validation.constraints.NotNull;

public class InsertForm {
	
	public InsertForm() {}

    public InsertForm(String contents) {
		super();
		this.contents = contents;
	}
    
    @NotNull
    private String contents;
    private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
