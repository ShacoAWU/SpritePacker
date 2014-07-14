SpritePacker
============

With these class you can easily add and load sprites from your cache.

Examples of how to use these classes:

			      /* Sprite unpacking */
            drawLoadingText(80, "Unpacking sprites");
            //chat
            SpriteUnpacker chatUnpacker = new SpriteUnpacker(configLoader.getDataForName("chat"));
            chatUnpacker.unpackSprites();
            chatArea = new Sprite(chatUnpacker.getSpriteBytes("chatarea"));
            chatButtons = new Sprite(chatUnpacker.getSpriteBytes("chatbuttons"));
            chatButtonH = new Sprite(chatUnpacker.getSpriteBytes("chathover"));
            chatButtonC = new Sprite(chatUnpacker.getSpriteBytes("chatclicked"));
            chatButtonHC = new Sprite(chatUnpacker.getSpriteBytes("chatclickedh"));
            reportH = new Sprite(chatUnpacker.getSpriteBytes("reporthover"));
            chatUnpacker.unloadSprites();
            
            
            /* Sprite packing */
            SpritePacker packer = new SpritePacker("./sprites/", "./chat.dat");
            ArrayList<Sprite> sprites = packer.getSprites();
            packer.buildCache(false, sprites);
            packer.unloadSprites();
