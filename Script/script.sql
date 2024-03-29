USE [testviamatica]
GO
/****** Object:  Table [dbo].[tb_opcion]    Script Date: 2/29/2024 11:54:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_opcion](
	[id_opcion] [int] IDENTITY(1,1) NOT NULL,
	[state] [varchar](1) NULL,
	[name] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_opcion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tb_permiso]    Script Date: 2/29/2024 11:54:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_permiso](
	[id_opcion] [int] NULL,
	[id_permiso] [int] IDENTITY(1,1) NOT NULL,
	[id_rol] [int] NULL,
	[state] [varchar](1) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_permiso] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tb_persona]    Script Date: 2/29/2024 11:54:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_persona](
	[id_persona] [int] IDENTITY(1,1) NOT NULL,
	[state] [varchar](1) NULL,
	[fecha_nacimiento] [datetime2](6) NULL,
	[identificacion] [varchar](10) NULL,
	[apellidos] [varchar](60) NULL,
	[nombres] [varchar](60) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_persona] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tb_rol]    Script Date: 2/29/2024 11:54:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_rol](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[state] [varchar](1) NULL,
	[name] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tb_usuario]    Script Date: 2/29/2024 11:54:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_usuario](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_persona] [int] NULL,
	[state] [varchar](1) NULL,
	[username] [varchar](50) NULL,
	[password] [varchar](100) NULL,
	[email] [varchar](120) NULL,
	[id_rol] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[tb_opcion] ON 

INSERT [dbo].[tb_opcion] ([id_opcion], [state], [name]) VALUES (1, N'A', N'Dashborad')
INSERT [dbo].[tb_opcion] ([id_opcion], [state], [name]) VALUES (2, N'A', N'Report')
INSERT [dbo].[tb_opcion] ([id_opcion], [state], [name]) VALUES (3, N'A', N'Products')
SET IDENTITY_INSERT [dbo].[tb_opcion] OFF
GO
SET IDENTITY_INSERT [dbo].[tb_permiso] ON 

INSERT [dbo].[tb_permiso] ([id_opcion], [id_permiso], [id_rol], [state]) VALUES (1, 1, 1, N'A')
INSERT [dbo].[tb_permiso] ([id_opcion], [id_permiso], [id_rol], [state]) VALUES (2, 2, 1, N'A')
SET IDENTITY_INSERT [dbo].[tb_permiso] OFF
GO
SET IDENTITY_INSERT [dbo].[tb_persona] ON 

INSERT [dbo].[tb_persona] ([id_persona], [state], [fecha_nacimiento], [identificacion], [apellidos], [nombres]) VALUES (1, N'A', CAST(N'1997-09-29T00:00:00.0000000' AS DateTime2), N'0987654321', N'Ricardo Reyes', N'Carlos Steven')
SET IDENTITY_INSERT [dbo].[tb_persona] OFF
GO
SET IDENTITY_INSERT [dbo].[tb_rol] ON 

INSERT [dbo].[tb_rol] ([id], [state], [name]) VALUES (1, N'A', N'Administrador')
INSERT [dbo].[tb_rol] ([id], [state], [name]) VALUES (2, N'A', N'Cliente')
SET IDENTITY_INSERT [dbo].[tb_rol] OFF
GO
SET IDENTITY_INSERT [dbo].[tb_usuario] ON 

INSERT [dbo].[tb_usuario] ([id], [id_persona], [state], [username], [password], [email], [id_rol]) VALUES (1, NULL, N'A', NULL, N'$2a$10$sR/RezV9vT42EHacNX3s6efjnPb8WqLaQl22GNkVFfYwSERtcADGW', N'cricardor@gmail.com', 1)
SET IDENTITY_INSERT [dbo].[tb_usuario] OFF
GO
ALTER TABLE [dbo].[tb_permiso]  WITH CHECK ADD  CONSTRAINT [FK21djf3ey95hfurep89w0mdptc] FOREIGN KEY([id_opcion])
REFERENCES [dbo].[tb_opcion] ([id_opcion])
GO
ALTER TABLE [dbo].[tb_permiso] CHECK CONSTRAINT [FK21djf3ey95hfurep89w0mdptc]
GO
ALTER TABLE [dbo].[tb_permiso]  WITH CHECK ADD  CONSTRAINT [FKdo55eo3vx1t1a6xfxbfs7qrpd] FOREIGN KEY([id_rol])
REFERENCES [dbo].[tb_rol] ([id])
GO
ALTER TABLE [dbo].[tb_permiso] CHECK CONSTRAINT [FKdo55eo3vx1t1a6xfxbfs7qrpd]
GO
ALTER TABLE [dbo].[tb_usuario]  WITH CHECK ADD  CONSTRAINT [FK6mxbmhthgow2y4dv0l13yngc5] FOREIGN KEY([id_rol])
REFERENCES [dbo].[tb_rol] ([id])
GO
ALTER TABLE [dbo].[tb_usuario] CHECK CONSTRAINT [FK6mxbmhthgow2y4dv0l13yngc5]
GO
ALTER TABLE [dbo].[tb_usuario]  WITH CHECK ADD  CONSTRAINT [FKnm0x2fw8u9l8bto7pwhwcpb2o] FOREIGN KEY([id_persona])
REFERENCES [dbo].[tb_persona] ([id_persona])
GO
ALTER TABLE [dbo].[tb_usuario] CHECK CONSTRAINT [FKnm0x2fw8u9l8bto7pwhwcpb2o]
GO
/****** Object:  StoredProcedure [dbo].[SP_GetUserByEmail]    Script Date: 2/29/2024 11:54:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetUserByEmail]
@email NVARCHAR(100)
AS
BEGIN
    SELECT * FROM tb_usuario WHERE state='A' AND email = @email;
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_ListUser]    Script Date: 2/29/2024 11:54:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_ListUser] 
    @state NVARCHAR(1)
AS
BEGIN
    SELECT * FROM tb_usuario WHERE state = @state;
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_searchPermissionByIdUserType]    Script Date: 2/29/2024 11:54:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_searchPermissionByIdUserType]
@id_rol INT
AS
BEGIN
    SELECT * FROM tb_permiso WHERE state='A' AND id_rol=@id_rol;
END;
GO
