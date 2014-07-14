SpritePacker
============

With these class you can easily add and load sprites from your cache.

Examples of how to use these classes:

	    /* Sprite unpacking */
            SpriteUnpacker chatUnpacker = new SpriteUnpacker(configLoader.getDataForName("chat"));
            chatUnpacker.unpackSprites();
            chatArea = new Sprite(chatUnpacker.getSpriteBytes("chatarea"));
            chatButtons = new Sprite(chatUnpacker.getSpriteBytes("chatbuttons"));
            chatUnpacker.unloadSprites();
            
            
            /* Sprite packing */
            SpritePacker packer = new SpritePacker("./sprites/", "./chat.dat");
            ArrayList<Sprite> sprites = packer.getSprites();
            packer.buildCache(false, sprites);
            packer.unloadSprites();
