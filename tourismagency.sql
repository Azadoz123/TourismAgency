PGDMP  
                     |            tourismagency    15.5    16.1 *    4           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            5           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            6           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            7           1262    16769    tourismagency    DATABASE     �   CREATE DATABASE tourismagency WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_T�rkiye.1254';
    DROP DATABASE tourismagency;
                postgres    false            �            1259    16779    hotel    TABLE     c  CREATE TABLE public.hotel (
    id integer NOT NULL,
    name character varying NOT NULL,
    address character varying NOT NULL,
    phone character varying NOT NULL,
    star character varying NOT NULL,
    car_parking boolean,
    wifi boolean,
    pool boolean,
    fitness boolean,
    concierge boolean,
    spa boolean,
    room_service boolean
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    16778    hotel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    217            �            1259    16801    pension    TABLE     }   CREATE TABLE public.pension (
    id integer NOT NULL,
    type character varying NOT NULL,
    hotel_id integer NOT NULL
);
    DROP TABLE public.pension;
       public         heap    postgres    false            �            1259    16787    pension2    TABLE     e  CREATE TABLE public.pension2 (
    id integer NOT NULL,
    ultra_all_include boolean NOT NULL,
    all_include boolean NOT NULL,
    room_breakfast boolean NOT NULL,
    full_pension boolean NOT NULL,
    half_pension boolean NOT NULL,
    only_bed boolean NOT NULL,
    full_credit_not_including_alcohol boolean NOT NULL,
    hotel_id integer NOT NULL
);
    DROP TABLE public.pension2;
       public         heap    postgres    false            �            1259    16786    pension_id_seq    SEQUENCE     �   ALTER TABLE public.pension2 ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    219            �            1259    16800    pension_id_seq1    SEQUENCE     �   ALTER TABLE public.pension ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pension_id_seq1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    223            �            1259    16835    reservation    TABLE     �  CREATE TABLE public.reservation (
    id integer NOT NULL,
    check_in_date date NOT NULL,
    check_out_date date NOT NULL,
    number_of_child integer NOT NULL,
    number_of_adult integer NOT NULL,
    guest_count integer NOT NULL,
    total_price double precision NOT NULL,
    guest_citizen_id character varying NOT NULL,
    guest_name character varying NOT NULL,
    guest_phone character varying NOT NULL,
    guest_mail character varying NOT NULL,
    room_id integer NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    16827    reservation2    TABLE     �  CREATE TABLE public.reservation2 (
    id integer NOT NULL,
    room_id integer NOT NULL,
    check_in_date date NOT NULL,
    check_out_date date NOT NULL,
    total_price double precision NOT NULL,
    guest_count integer NOT NULL,
    guest_citizen_id character varying NOT NULL,
    guest_mail character varying NOT NULL,
    guest_phone character varying NOT NULL,
    guest_name character varying NOT NULL,
    number_of_child integer,
    number_of_adult integer
);
     DROP TABLE public.reservation2;
       public         heap    postgres    false            �            1259    16832    reservation_id_seq    SEQUENCE     �   ALTER TABLE public.reservation2 ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    226            �            1259    16834    reservation_id_seq1    SEQUENCE     �   ALTER TABLE public.reservation ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_id_seq1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    229            �            1259    16809    room    TABLE       CREATE TABLE public.room (
    id integer NOT NULL,
    number_of_bed integer NOT NULL,
    area_of_number integer NOT NULL,
    tv boolean NOT NULL,
    bar boolean NOT NULL,
    game_console boolean NOT NULL,
    money_case boolean NOT NULL,
    projection boolean NOT NULL,
    stock integer NOT NULL,
    child_price double precision NOT NULL,
    adult_price double precision NOT NULL,
    hotel_id integer NOT NULL,
    season_id integer NOT NULL,
    pension_id integer NOT NULL,
    type character varying NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    16808    room_Id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."room_Id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    225            �            1259    16795    season    TABLE     �   CREATE TABLE public.season (
    id integer NOT NULL,
    start_time date NOT NULL,
    finish_time date NOT NULL,
    hotel_id integer NOT NULL
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    16794    season_id_seq    SEQUENCE     �   ALTER TABLE public.season ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    221            �            1259    16771    user    TABLE     �   CREATE TABLE public."user" (
    id integer NOT NULL,
    name character varying NOT NULL,
    password character varying NOT NULL,
    role character varying NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    16770    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    215            %          0    16779    hotel 
   TABLE DATA              COPY public.hotel (id, name, address, phone, star, car_parking, wifi, pool, fitness, concierge, spa, room_service) FROM stdin;
    public          postgres    false    217   Z3       +          0    16801    pension 
   TABLE DATA           5   COPY public.pension (id, type, hotel_id) FROM stdin;
    public          postgres    false    223   �3       '          0    16787    pension2 
   TABLE DATA           �   COPY public.pension2 (id, ultra_all_include, all_include, room_breakfast, full_pension, half_pension, only_bed, full_credit_not_including_alcohol, hotel_id) FROM stdin;
    public          postgres    false    219   a4       1          0    16835    reservation 
   TABLE DATA           �   COPY public.reservation (id, check_in_date, check_out_date, number_of_child, number_of_adult, guest_count, total_price, guest_citizen_id, guest_name, guest_phone, guest_mail, room_id) FROM stdin;
    public          postgres    false    229   �4       .          0    16827    reservation2 
   TABLE DATA           �   COPY public.reservation2 (id, room_id, check_in_date, check_out_date, total_price, guest_count, guest_citizen_id, guest_mail, guest_phone, guest_name, number_of_child, number_of_adult) FROM stdin;
    public          postgres    false    226   5       -          0    16809    room 
   TABLE DATA           �   COPY public.room (id, number_of_bed, area_of_number, tv, bar, game_console, money_case, projection, stock, child_price, adult_price, hotel_id, season_id, pension_id, type) FROM stdin;
    public          postgres    false    225   g5       )          0    16795    season 
   TABLE DATA           G   COPY public.season (id, start_time, finish_time, hotel_id) FROM stdin;
    public          postgres    false    221   6       #          0    16771    user 
   TABLE DATA           :   COPY public."user" (id, name, password, role) FROM stdin;
    public          postgres    false    215   k6       8           0    0    hotel_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.hotel_id_seq', 6, true);
          public          postgres    false    216            9           0    0    pension_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.pension_id_seq', 1, true);
          public          postgres    false    218            :           0    0    pension_id_seq1    SEQUENCE SET     =   SELECT pg_catalog.setval('public.pension_id_seq1', 5, true);
          public          postgres    false    222            ;           0    0    reservation_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.reservation_id_seq', 1, false);
          public          postgres    false    227            <           0    0    reservation_id_seq1    SEQUENCE SET     B   SELECT pg_catalog.setval('public.reservation_id_seq1', 10, true);
          public          postgres    false    228            =           0    0    room_Id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public."room_Id_seq"', 11, true);
          public          postgres    false    224            >           0    0    season_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.season_id_seq', 7, true);
          public          postgres    false    220            ?           0    0    user_user_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.user_user_id_seq', 9, true);
          public          postgres    false    214            �           2606    16785    hotel hotel_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    217            �           2606    16793    pension2 pension_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.pension2
    ADD CONSTRAINT pension_pkey PRIMARY KEY (id);
 ?   ALTER TABLE ONLY public.pension2 DROP CONSTRAINT pension_pkey;
       public            postgres    false    219            �           2606    16807    pension pension_pkey1 
   CONSTRAINT     S   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_pkey1 PRIMARY KEY (id);
 ?   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_pkey1;
       public            postgres    false    223            �           2606    16813    room room_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    225            �           2606    16799    season season_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    221            �           2606    16775    user user_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    215            %   �   x�M�M
�0��/Gq!$�@�ݹ��C� i�����C���)�{����H4�y���e
� �R�� ���(�Ժ�Li�3��
�+�'7Sh�[� c�.;����L^�|�'d
~T44��x�i=�Q���w�!�E5�      +   U   x�]�;�  й=�	�EJ$V0|������2)�h.���`�B���И�D}���_�"�����A����s-�� �w��      '      x�3�,�LC��\1z\\\ 85      1   y   x�u�;1Ckq�d�M�{lc�.}�`/W��Bo�S[_J�<�1,��R
P���D��:�R�N�9S�?�]�͢_�)�Z��{q=�9�Zkx,�Pgt�swe� Zs�{���-"_y,3�      .   H   x�3�4�4202�50"ӈ�����ӈ�А311��8�S8c��������4���#��=... �4E      -   �   x�U��
1D���HҦ[��"�®�	����l65�2PH�f2	�h��u&՜�����Jy:�)�D���'O�/��?u����2�?�(s@�{�ɠYz��1��[-�� ->����u'ٳ�M��V{��ı����
�5�      )   S   x�u���0C��.E��a����@���s"s�ĩe�+5�T�M�E����"{)���y�=Sy_h4��Y� 7��>      #   S   x�3�t�JL�442615�tt����2�t��M-B��R9�W� �HWW.��܂����T�v��%�sjQjDjD� �j?     