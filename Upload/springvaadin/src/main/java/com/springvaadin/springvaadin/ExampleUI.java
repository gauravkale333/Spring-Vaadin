package com.springvaadin.springvaadin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;

import com.springvaadin.springvaadin.model.User;
import com.springvaadin.springvaadin.repository.UserRepo;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Image;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
public class ExampleUI extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4961155042318612135L;
	private final UserRepo userRepo;

	@Autowired
	public ExampleUI(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}

	private DateField date;
	private TextField Name;
	private Upload up;
	private Image imagePhot;
	User u = new User();

	@Override
	protected void init(VaadinRequest request) {
		setLayout();
	}
	
	private void setLayout() {
		VerticalLayout v = new VerticalLayout();
		CssLayout css = new CssLayout();
		Name = new TextField();
		Name.addAttachListener(event -> TextFieldInfo());

		css.addComponents(Name);
		css.addStyleName(ValoTheme.LAYOUT_WELL);

		date = new DateField();

		up = new Upload();

		up.addAttachListener(event -> uploadfile());
		imagePhot = new Image();
		
		Button button = new Button("Click");
		button.addClickListener(event -> adduser());
		v.addComponents(css, date, up, button, imagePhot);
		setContent(v);

	}

	//Upload and display Photo

	private void uploadfile() {
		imagePhot.setHeight("150px");
		imagePhot.setWidth("120px");
		Receiver rs = new Receiver() {
			private static final long serialVersionUID = 1L;

			@Override
			public OutputStream receiveUpload(String filename, String mimeType) {
				try {
					System.out.println(mimeType);
					FileOutputStream fout = new FileOutputStream(filename);
					if (filename == null || filename.isEmpty()) {
						FileResource resource = new FileResource(new File(
								"D://dhanu//drive F//dhanu//pendrive//dhanshri//Camera//IMG_20170912_115200.jpg"));
						imagePhot.setSource(resource);
					} else {
						u.setPhotoPath(filename);
						FileResource resource = new FileResource(new File(filename));
						imagePhot.setSource(resource);
					}
					return fout;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
		up.setReceiver(rs);
	}

	private void TextFieldInfo() {
		Name.setPlaceholder("Name");

		Name.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
		Name.addValueChangeListener(event -> {
			int id = Name.getCursorPosition();
			if (id == 0) {
				Name.setIcon(VaadinIcons.PENCIL);
			} else if (id >= 5) {
				Name.setIcon(VaadinIcons.CHECK_CIRCLE);

			} else {
				Name.setIcon(VaadinIcons.CLOSE_CIRCLE);

			}
			id++;

		});
	}

	private void adduser() {
		System.out.println(date.getValue());
		LocalDate d = date.getValue();
		u.setName(Name.getValue());
		u.setbDate(localDateToCal(d));
		userRepo.save(u);

	}

	public static Date localDateToCal(LocalDate date) {
		if (date == null) {
			return null;
		}
		return calTODate(GregorianCalendar.from(date.atStartOfDay(ZoneId.systemDefault())));
	}

	public static Date calTODate(Calendar calendar) {
		Date sqlDate = null;
		if (calendar != null) {
			sqlDate = new Date(calendar.getTimeInMillis());
		}
		return sqlDate;
	}

}
